package com.xingtao.xingtaomall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.xingtaomall.order.entity.OrderReturnApplyEntity;

import java.util.Map;

/**
 * 订单退货申请
 *
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:35:26
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

