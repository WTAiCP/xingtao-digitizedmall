package com.xingtao.xingtaomall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.xingtaomall.ware.entity.WareInfoEntity;
import com.xingtao.xingtaomall.ware.vo.FareVo;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:39:03
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    FareVo getFare(Long addrId);
}

