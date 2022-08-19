package com.xingtao.xingtaomall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-06-01 20:33
 **/

@Data
public class PurchaseDoneVo {

    @NotNull(message = "id不允许为空")
    private Long id;

    private List<PurchaseItemDoneVo> items;

}
