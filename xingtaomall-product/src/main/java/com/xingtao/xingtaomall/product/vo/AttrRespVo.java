package com.xingtao.xingtaomall.product.vo;

import lombok.Data;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.product.vo
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/5/8
 */
@Data
public class AttrRespVo extends AttrVo{

    /**
     * "catelogName": "手机/数码/手机", //所属分类名字
     * 			"groupName": "主体", //所属分组名字
     */

    private String catelogName;

    private String groupName;

    //分类完整路径
    private Long[] catelogPath;
}
