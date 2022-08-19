package com.xingtao.xingtaomall.product.feign;

import com.xingtao.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.product.feign
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/5/18
 */
@FeignClient("xingtaomall-ware")
public interface WareFeignService {

    //查询sku是否有库存
    @PostMapping(value = "/ware/waresku/hasStock")
    public R getSkuHasStock(@RequestBody List<Long> skuIds);
}
