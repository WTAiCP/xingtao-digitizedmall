package com.xingtao.xingtaomall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.xingtaomall.coupon.entity.CouponSpuCategoryRelationEntity;

import java.util.Map;

/**
 * 优惠券分类关联
 *
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:11:16
 */
public interface CouponSpuCategoryRelationService extends IService<CouponSpuCategoryRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

