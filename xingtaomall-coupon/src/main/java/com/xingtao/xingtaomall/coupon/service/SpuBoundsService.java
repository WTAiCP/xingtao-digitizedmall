package com.xingtao.xingtaomall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.xingtaomall.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:11:16
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

