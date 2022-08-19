package com.xingtao.xingtaomall.search.service;

import com.xingtao.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.search.service
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/5/18
 */
public interface ProductSaveService {

    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
