package com.xingtao.xingtaomall.product.app;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xingtao.xingtaomall.product.entity.CategoryEntity;
import com.xingtao.xingtaomall.product.service.CategoryService;
import com.xingtao.common.utils.R;



/**
 * 商品三级分类
 *
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-11 20:07:31
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查出所有分类以及子分类，以树形结果组装起来
     */
    @RequestMapping("/list/tree")
    //@RequiresPermissions("product:category:list")
    public R list(){

        List<CategoryEntity> entities = categoryService.listWithTree();
        return R.ok().put("data", entities);
    }


    /**
     * 信息
     * 根据id查找信息
     * {catId}表示动态获取（前端中）
     */
    @RequestMapping("/info/{catId}")
    //@RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateCascade(category);

        return R.ok();
    }

    /**
     * 拖曳功能实现
     * 批量修改
     * 前端回显的数据 进行修改，不回显的即为null
     * ById：CategoryEntity的catId
     */
    @RequestMapping("/update/sort")
    //@RequiresPermissions("product:category:update")
    public R updateSort(@RequestBody CategoryEntity[] category){

        categoryService.updateBatchById(Arrays.asList(category));

        return R.ok();
    }

    /**
     * 删除
     * @RequestBody ：功能：获取请·求体，必须发送post请求
     * springMVC自动将请求体的数据（json）转为对应的对象，这里的对象是Long[]
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds){

		//categoryService.removeByIds(Arrays.asList(catIds)); 逆向工程生成的代码，过于简单，需要自定义

        //1、检查当前菜单是否被其他地方引用

        categoryService.removeMenuByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
