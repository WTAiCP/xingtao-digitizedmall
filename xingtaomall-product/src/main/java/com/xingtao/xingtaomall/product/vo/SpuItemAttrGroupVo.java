package com.xingtao.xingtaomall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-06-19 18:18
 **/

@Data
@ToString
public class SpuItemAttrGroupVo {

    private String groupName;

    private List<com.xingtao.xingtaomall.product.vo.Attr> attrs;

}
