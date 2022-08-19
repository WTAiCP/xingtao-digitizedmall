package com.xingtao.xingtaomall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.xingtaomall.ware.entity.PurchaseDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:39:03
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils  queryPage(Map<String, Object> params);

    List<PurchaseDetailEntity> listDetailByPurchaseId(Long id);
}

