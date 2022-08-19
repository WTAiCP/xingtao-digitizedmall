package com.xingtao.xingtaomall.order.dao;

import com.xingtao.xingtaomall.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 * 
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:35:26
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {
	
}
