package com.xingtao.xingtaomall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xingtao.xingtaomall.product.dao.BrandDao;
import com.xingtao.xingtaomall.product.dao.CategoryDao;
import com.xingtao.xingtaomall.product.entity.BrandEntity;
import com.xingtao.xingtaomall.product.entity.CategoryEntity;
import com.xingtao.xingtaomall.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.common.utils.Query;

import com.xingtao.xingtaomall.product.dao.CategoryBrandRelationDao;
import com.xingtao.xingtaomall.product.entity.CategoryBrandRelationEntity;
import com.xingtao.xingtaomall.product.service.CategoryBrandRelationService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    BrandDao brandDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryBrandRelationDao relationDao;

    @Autowired
    BrandService brandService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        //1、查询详细名字
        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        this.save(categoryBrandRelation);
    }

    /**
     * 保证冗余数据一致性
     * @param brandId
     * @param name
     */
    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelationEntity relationEntity = new CategoryBrandRelationEntity();
        relationEntity.setBrandId(brandId);
        relationEntity.setBrandName(name);
        this.update(relationEntity, new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id",brandId));
    }

    /**
     * 保证冗余数据一致
     * @param catId
     * @param name
     */
    @Override
    public void updateCascade(Long catId, String name) {
        //与上面的updateBrand实现的功能相同，但是写法不同：Brand使用mybatis-plus封装的
        //Cascade在baseMaper中，自己自定义了一个sql语句，其实里面的内容都一样
        this.baseMapper.updateCategory(catId,name);
    }

    @Override
    public List<BrandEntity> getBrandsByCatId(Long catId) {

        List<CategoryBrandRelationEntity> catelogId = relationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));

        List<BrandEntity> collect = catelogId.stream().map(item -> {
            Long brandId = item.getBrandId();
            //查询品牌的详情
            BrandEntity byId = brandService.getById(brandId);
            return byId;
        }).collect(Collectors.toList());

        return collect;
    }

}