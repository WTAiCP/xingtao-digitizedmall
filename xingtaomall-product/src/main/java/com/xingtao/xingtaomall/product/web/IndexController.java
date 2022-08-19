package com.xingtao.xingtaomall.product.web;


import com.xingtao.xingtaomall.product.entity.CategoryEntity;
import com.xingtao.xingtaomall.product.service.CategoryService;
import com.xingtao.xingtaomall.product.vo.Catelog2Vo;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  web包：处理本服务的前端页面
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-06-08 14:14
 **/

@Controller
public class IndexController {

    @Resource
    private CategoryService categoryService;

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = {"/","index.html"})
    private String indexPage(Model model) {

        //1、查出所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();
        model.addAttribute("categories",categoryEntities);
        return "index";  //已配置前缀后后缀
    }


    /**
     * 查询二级三级分类
     * @return
     */
    //index/json/catalog.json
    @GetMapping(value = "index/json/catalog.json")
    @ResponseBody
    public Map<String, List<Catelog2Vo>> getCatalogJson() {

        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();

        return catalogJson;

    }


    @ResponseBody
    @GetMapping(value = "/hello")
    public String hello() {

        //1、获取一把锁，只要锁的名字一样，就是同一把锁
        //redisson下的Rlock继承了JUC包下的lock接口
        RLock myLock = redisson.getLock("my-lock");

        //2、加锁
        myLock.lock();      //阻塞式等待。如果加不到锁就一直等。默认加的锁都是30s，如果过期则自动续期
        //1）、锁的自动续期，如果业务超长，运行期间自动锁上新的30s。解决的问题：不用担心业务时间长，锁自动过期被删掉
        //2）、加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认会在30s内自动过期，不会产生死锁问题（没有执行解锁时）
        // myLock.lock(10,TimeUnit.SECONDS);   //默认30s，这里指定10秒钟自动解锁,自动解锁时间一定要大于业务执行时间
        //问题：在锁时间到了以后，不会自动续期
        //1、如果我们传递了锁的超时时间，就发送给redis执行脚本(Lua脚本)，进行占锁，默认超时就是 我们制定的时间
        //2、如果我们未指定锁的超时时间，就使用 lockWatchdogTimeout = 30 * 1000 【看门狗默认时间】
        //只要占锁成功，就会启动一个定时任务【重新给锁设置过期时间，新的过期时间就是看门狗的默认时间】,每隔10秒都会自动的再次续期，续成30秒
        // internalLockLeaseTime 【看门狗时间】 / 3，(三分之一的看门狗时间) == 10s  每10s续一次
        //建议使用myLock.lock(10,TimeUnit.SECONDS); 指定一个时间，省掉续期操作，手动解锁，即使没有解锁，在指定时间后也会过期
        try {
            System.out.println("加锁成功，执行业务..." + Thread.currentThread().getId());
            try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //3、解锁  假设解锁代码没有运行，Redisson会不会出现死锁——不会死锁：TTL看门狗
            System.out.println("释放锁..." + Thread.currentThread().getId());
            myLock.unlock();
        }

        return "hello";
    }


    /**
     * 保证一定能读到最新数据，修改期间，写锁是一个排它锁（互斥锁、独享锁），读锁是一个共享锁
     * 写锁没释放读锁必须等待
     * 读 + 读 ：相当于无锁，并发读，只会在Redis中记录好，所有当前的读锁。他们都会同时加锁成功
     * 写 + 读 ：必须等待写锁释放  可以保证读出的数据是最新的
     * 写 + 写 ：阻塞方式
     * 读 + 写 ：有读锁。写也需要等待
     * 只要有读或者写的存都必须等待
     * @return
     */
    @GetMapping(value = "/write")
    @ResponseBody
    public String writeValue() {
        String s = "";
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("rw-lock");
        RLock rLock = readWriteLock.writeLock();
        try {
            //1、改数据加写锁，读数据加读锁
            rLock.lock();
            s = UUID.randomUUID().toString();
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            ops.set("writeValue",s);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }

        return s;
    }

    @GetMapping(value = "/read")
    @ResponseBody
    public String readValue() {
        String s = "";
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("rw-lock");
        //加读锁
        RLock rLock = readWriteLock.readLock();
        try {
            rLock.lock();
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            s = ops.get("writeValue");
            try { TimeUnit.SECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }

        return s;
    }


    /**
     * 信号量
     * 车库停车
     * 3车位
     * 信号量也可以做分布式限流
     */
    @GetMapping(value = "/park")
    @ResponseBody
    public String park() throws InterruptedException {

        RSemaphore park = redisson.getSemaphore("park");
        park.acquire();     //获取一个信号、获取一个值,占一个车位  阻塞等待
        boolean flag = park.tryAcquire();

        if (flag) {
            //执行业务
        } else {
            return "error";
        }

        return "ok=>" + flag;
    }

    @GetMapping(value = "/go")
    @ResponseBody
    public String go() {
        RSemaphore park = redisson.getSemaphore("park");
        park.release();     //释放一个车位
        return "ok";
    }


    /**
     * 放假、锁门
     * 5个班，全部走完，我们才可以锁大门
     * 分布式闭锁
     */

    @GetMapping(value = "/lockDoor")
    @ResponseBody
    public String lockDoor() throws InterruptedException {

        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.trySetCount(5);
        door.await();       //等待闭锁完成

        return "放假了...";
    }

    @GetMapping(value = "/gogogo/{id}")
    @ResponseBody
    public String gogogo(@PathVariable("id") Long id) {
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.countDown();       //计数-1

        return id + "班的人都走了...";
    }

}
