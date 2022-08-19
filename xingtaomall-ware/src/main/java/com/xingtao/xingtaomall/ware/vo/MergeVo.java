package com.xingtao.xingtaomall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.ware.vo
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/5/13
 */
@Data
public class MergeVo {
    private Long purchaseId;

    private List<Long> items;
}
