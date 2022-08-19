package com.xingtao.xingtaomall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.xingtaomall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:19:08
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

