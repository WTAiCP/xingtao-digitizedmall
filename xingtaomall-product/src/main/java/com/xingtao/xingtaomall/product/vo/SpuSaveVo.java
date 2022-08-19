/** Copyright 2022 bejson.com */
package com.xingtao.xingtaomall.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2022-05-31 11:3:26
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */

@Data
public class SpuSaveVo {

  private String spuName;
  private String spuDescription;
  private Long catalogId;
  private Long brandId;
  private BigDecimal weight;
  private int publishStatus;
  private List<String> decript;
  private List<String> images;
  private com.xingtao.xingtaomall.product.vo.Bounds bounds;
  private List<com.xingtao.xingtaomall.product.vo.BaseAttrs> baseAttrs;
  private List<com.xingtao.xingtaomall.product.vo.Skus> skus;


}
