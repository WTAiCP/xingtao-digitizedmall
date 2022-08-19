package com.xingtao.xingtaomall.search.controller;

import com.xingtao.common.exception.BizCodeEnum;
import com.xingtao.common.to.es.SkuEsModel;
import com.xingtao.common.utils.R;
import com.xingtao.xingtaomall.search.service.ProductSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.search.controller
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/5/18
 */
@RestController
@RequestMapping("/search/save")
public class ElasticSaveController {

    @Autowired
    private ProductSaveService productSaveService;

    /**
     * 上架商品
     * @param skuEsModels
     * @return
     */
    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
        boolean status=false;
        try {
            status = productSaveService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            //log.error("商品上架错误{}",e);

            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnum.PRODUCT_UP_EXCEPTION.getMessage());
        }

        if(status){
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnum.PRODUCT_UP_EXCEPTION.getMessage());
        }else {
            return R.ok();
        }
    }
}
