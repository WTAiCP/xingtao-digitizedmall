package com.xingtao.xingtaomall.order.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description: 锁定库存的vo
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-05 10:52
 **/

@Data
public class WareSkuLockVo {

    private String orderSn;

    /** 需要锁住的所有库存信息 **/
    private List<OrderItemVo> locks;



}
