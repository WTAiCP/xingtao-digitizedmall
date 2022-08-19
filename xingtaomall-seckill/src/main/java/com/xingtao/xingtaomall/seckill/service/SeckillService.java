package com.xingtao.xingtaomall.seckill.service;

import com.xingtao.xingtaomall.seckill.to.SeckillSkuRedisTo;

import java.util.List;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.seckill.service
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/6/23
 */
public interface SeckillService {
    /**
     * 上架三天需要的秒杀商品
     */
    void uploadSeckillSkuLatest3Days();

    List<SeckillSkuRedisTo> getCurrentSeckillSkus();

    /**
     * 根据skuId查询商品是否参加秒杀活动
     * @param skuId
     * @return
     */
    SeckillSkuRedisTo getSkuSeckilInfo(Long skuId);

    String kill(String killId, String key, Integer num);

}
