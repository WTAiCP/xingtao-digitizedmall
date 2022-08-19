package com.xingtao.xingtaomall.product.feign;

import com.xingtao.common.to.SkuReductionTo;
import com.xingtao.common.to.SpuBoundTo;
import com.xingtao.common.utils.R;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 远程调用coupon服务
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.product.feign
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/5/12
 */
@FeignClient("xingtaomall-coupon")
public interface CouponFeignService {

    /**
     * * 1、CouponFeignService.saveSpuBounds(spuBoundTo);
     *            1）、@RequestBody将这个对象转为json。
     *            2）、找到gulimall-coupon服务，给/coupon/spubounds/save发送请求。
     *                将上一步转的json放在请求体位置，发送请求；
     *            3）、对方服务收到请求。请求体里有json数据。
     *                (@RequestBody SpuBoundsEntity spuBounds)；将请求体的json转为SpuBoundsEntity；
     *      只要json数据模型是兼容的。双方服务无需使用同一个to
     * @param spuBoundTo
     * @return
     */
    @PostMapping("coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
