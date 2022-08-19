package com.xingtao.xingtaomall.product.web;

import com.xingtao.xingtaomall.product.service.SkuInfoService;
import com.xingtao.xingtaomall.product.vo.SkuItemVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @Description: 商品详情信息
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-06-19 11:21
 **/

@Controller
public class ItemController {

    @Resource
    private SkuInfoService skuInfoService;

    /**
     * 展示当前sku的详情
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable("skuId") Long skuId, Model model) throws ExecutionException, InterruptedException {

        System.out.println("准备查询" + skuId + "详情");

        SkuItemVo vos = skuInfoService.item(skuId);
        
        model.addAttribute("item",vos);

        return "item";
    }
}