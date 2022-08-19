package com.xingtao.xingtaomall.order.dao;

import com.xingtao.xingtaomall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:35:26
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
