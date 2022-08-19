package com.xingtao.xingtaomall.order.to;


import com.xingtao.xingtaomall.order.entity.OrderEntity;
import com.xingtao.xingtaomall.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-04 23:04
 **/

@Data
public class OrderCreateTo {

    private OrderEntity order;

    /**订单项**/
    private List<OrderItemEntity> orderItems;

    /** 订单计算的应付价格 **/
    private BigDecimal payPrice;

    /** 运费 **/
    private BigDecimal fare;

}
