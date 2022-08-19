package com.xingtao.xingtaomall.order.dao;

import com.xingtao.xingtaomall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:35:26
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

    void updateOrderStatus(String orderSn, Integer code, Integer payType);
}
