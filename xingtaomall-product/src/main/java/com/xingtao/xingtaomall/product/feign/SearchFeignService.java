package com.xingtao.xingtaomall.product.feign;

import com.xingtao.common.to.es.SkuEsModel;
import com.xingtao.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-06-06 17:12
 **/

@FeignClient("xingtaomall-search")
public interface SearchFeignService {

    @PostMapping(value = "/search/save/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);

}
