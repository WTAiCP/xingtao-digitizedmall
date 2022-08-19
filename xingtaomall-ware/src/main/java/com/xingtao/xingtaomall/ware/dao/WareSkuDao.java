package com.xingtao.xingtaomall.ware.dao;

import com.xingtao.xingtaomall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存
 * 
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:39:03
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    void addStock(Long skuId, Long wareId, Integer skuNum);

    Long getSkuStock(@Param("skuId") Long skuId);

    List<Long> listWareIdHasSkuStock(Long skuId);

    /**
     * 锁定库存
     * @param skuId
     * @param wareId
     * @param num
     * @return
     */
    Long lockSkuStock(Long skuId, Long wareId, Integer num);

    void unLockStock(Long skuId, Long wareId, Integer num);
}
