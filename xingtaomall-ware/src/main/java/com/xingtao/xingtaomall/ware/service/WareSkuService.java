package com.xingtao.xingtaomall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xingtao.common.to.OrderTo;
import com.xingtao.common.to.mq.StockLockedTo;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.xingtaomall.ware.entity.WareSkuEntity;
import com.xingtao.xingtaomall.ware.vo.SkuHasStockVo;
import com.xingtao.xingtaomall.ware.vo.WareSkuLockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:39:03
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

    boolean orderLockStock(WareSkuLockVo vo);

    void unlockStock(StockLockedTo to);

    void unlockStock(OrderTo orderTo);
}

