package com.xingtao.xingtaomall.product.service.impl;

import com.xingtao.xingtaomall.product.service.CategoryBrandRelationService;
import com.xingtao.xingtaomall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.common.utils.Query;

import com.xingtao.xingtaomall.product.dao.BrandDao;
import com.xingtao.xingtaomall.product.entity.BrandEntity;
import com.xingtao.xingtaomall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //1、获取key
        String key = (String) params.get("key");
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(key)){
            queryWrapper.eq("brand_id",key).or().like("name",key);
        }

        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper

        );

        return new PageUtils(page);
    }

    /**
     * 数据库表冗余设计
     * 更行全部相关联的内容
     * 保证冗余字段的数据一致
     * @param brand
     */
    @Transactional  //开启事务
    @Override
    public void updateDetail(BrandEntity brand) {
        //更新品牌表
        baseMapper.updateById(brand);

        if (!StringUtils.isEmpty(brand.getName())) {
            categoryBrandRelationService.updateBrand(brand.getBrandId(),brand.getName());
        }

        //TODO  更新其他表保证冗余数据一致性
    }

}