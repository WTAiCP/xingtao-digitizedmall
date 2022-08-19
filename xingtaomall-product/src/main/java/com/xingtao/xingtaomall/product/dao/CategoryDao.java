package com.xingtao.xingtaomall.product.dao;

import com.xingtao.xingtaomall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-11 18:39:15
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
