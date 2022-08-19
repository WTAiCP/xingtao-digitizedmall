package com.xingtao.xingtaomall.member.dao;

import com.xingtao.xingtaomall.member.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-14 20:19:08
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
