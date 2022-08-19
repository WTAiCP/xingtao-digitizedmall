package com.xingtao.common.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:  商品上架时，es中保存的数据模型，封装成实体
 * 通过 REST API时，用实体封装的方式
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-06-06 14:07
 **/

@Data
public class SkuEsModel {

    private Long skuId;

    private Long spuId;

    private String skuTitle;

    private BigDecimal skuPrice;

    private String skuImg;

    private Long saleCount;

    private Boolean hasStock;

    private Long hotScore;

    private Long brandId;

    private Long catalogId;

    private String brandName;

    private String brandImg;

    private String catalogName;

    private List<Attrs> attrs;

    @Data
    public static class Attrs {

        private Long attrId;

        private String attrName;

        private String attrValue;

    }


}
