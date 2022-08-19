package com.xingtao.xingtaomall.product;


import com.xingtao.xingtaomall.product.entity.BrandEntity;
import com.xingtao.xingtaomall.product.service.BrandService;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
@RunWith(SpringRunner.class)
@SpringBootTest
public class XingtaomallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedissonClient redissonClient;


    @Test
    public void testRedisson() {
        System.out.println(redissonClient);
    }

    @Test
    public void testUpload() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        //保存
        ops.set("hello","world_1");
        //查询
        System.out.println(ops.get("hello"));
    }

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setName("华为");
        brandService.save(brandEntity);
        System.out.println("保存成功");
    }

}
