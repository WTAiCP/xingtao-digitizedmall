package com.xingtao.xingtaomall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.xingtaomall.member.entity.MemberStatisticsInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:19:08
 */
public interface MemberStatisticsInfoService extends IService<MemberStatisticsInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

