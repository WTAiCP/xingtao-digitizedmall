package com.xingtao.xingtaomall.product.app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.xingtao.xingtaomall.product.entity.AttrEntity;
import com.xingtao.xingtaomall.product.service.AttrAttrgroupRelationService;
import com.xingtao.xingtaomall.product.service.AttrService;
import com.xingtao.xingtaomall.product.service.CategoryService;
import com.xingtao.xingtaomall.product.vo.AttrGroupRelationVo;
import com.xingtao.xingtaomall.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xingtao.xingtaomall.product.entity.AttrGroupEntity;
import com.xingtao.xingtaomall.product.service.AttrGroupService;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.common.utils.R;



/**
 * 属性分组
 *
 * @author wangtao
 * @email wangtao@gmail.com
 * @date 2022-04-11 20:07:31
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("catelogId") Long catelogId){
        //PageUtils page = attrGroupService.queryPage(params);

        PageUtils page = attrGroupService.queryPage(params,catelogId);
        return R.ok().put("page", page);
    }

    /**
     * 获取属性分组有查询关联的其他属性
     * @param attrgroupId
     * @return
     */
    ///product/attrgroup/{attrgroupId}/attr/relation
    @GetMapping(value = "/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {

        List<AttrEntity> entities = attrService.getRelationAttr(attrgroupId);

        return R.ok().put("data",entities);
    }

    ///product/attrgroup/{catelogId}/withattr
    //获取分类下所有分组&关联属性
    @GetMapping(value = "/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId") Long catelogId) {

        //1、查出当前分类下的所有属性分组
        //2、查出每个属性分组下的所有属性
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);


        return R.ok().put("data", vos);
    }

        /**
         * 删除属性与分组的关联关系
         * @param vos
         * @return
         */
        //product/attrgroup/attr/relation/delete
        @PostMapping(value = "/attr/relation/delete")
        public R deleteRelation (@RequestBody AttrGroupRelationVo[]vos){

            attrService.deleteRelation(vos);

            return R.ok();
        }

        /**
         * 获取属性分组没有关联的其他属性
         */
        @GetMapping(value = "/{attrgroupId}/noattr/relation")
        public R attrNoattrRelation (@RequestParam Map < String, Object > params,
                @PathVariable Long attrgroupId){

            // List<AttrEntity> entities = attrService.getRelationAttr(attrgroupId);
            PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);

            return R.ok().put("page", page);
        }

        ///product/attrgroup/attr/relation
        @PostMapping(value = "/attr/relation")
        public R addRelation (@RequestBody List < AttrGroupRelationVo > vos) {

            attrAttrgroupRelationService.saveBatch(vos);

            return R.ok();

        }

        /**
         * 信息
         */
        @RequestMapping("/info/{attrGroupId}")
        //@RequiresPermissions("product:attrgroup:info")
        public R info (@PathVariable("attrGroupId") Long attrGroupId){
            AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

            Long catelogId = attrGroup.getCatelogId();
            Long[] path = categoryService.findCatelogPath(catelogId);

            attrGroup.setCatelogPath(path);
            return R.ok().put("attrGroup", attrGroup);
        }

        /**
         * 保存
         */
        @RequestMapping("/save")
        //@RequiresPermissions("product:attrgroup:save")
        public R save (@RequestBody AttrGroupEntity attrGroup){
            attrGroupService.save(attrGroup);

            return R.ok();
        }

        /**
         * 修改
         */
        @RequestMapping("/update")
        //@RequiresPermissions("product:attrgroup:update")
        public R update (@RequestBody AttrGroupEntity attrGroup){
            attrGroupService.updateById(attrGroup);

            return R.ok();
        }

        /**
         * 删除
         */
        @RequestMapping("/delete")
        //@RequiresPermissions("product:attrgroup:delete")
        public R delete (@RequestBody Long[]attrGroupIds){
            attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

            return R.ok();
        }
}
