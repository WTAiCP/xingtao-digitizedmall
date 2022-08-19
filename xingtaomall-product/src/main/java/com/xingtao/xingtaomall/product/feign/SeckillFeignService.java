package com.xingtao.xingtaomall.product.feign;


import com.xingtao.common.utils.R;
import com.xingtao.xingtaomall.product.fallback.SeckillFeignServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: 秒杀远程调用
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-10 15:53
 **/

@FeignClient(value = "xingtaomall-seckill",fallback = SeckillFeignServiceFallBack.class)
public interface SeckillFeignService {

    /**
     * 根据skuId查询商品是否参加秒杀活动
     * @param skuId
     * @return
     */
    @GetMapping(value = "/sku/seckill/{skuId}")
    R getSkuSeckilInfo(@PathVariable("skuId") Long skuId);

}
