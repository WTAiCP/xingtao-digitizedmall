package com.xingtao.xingtaomall.ware.vo;

import lombok.Data;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-05 10:57
 **/

@Data
public class LockStockResultVo {

    private Long skuId;

    private Integer num;

    /** 是否锁定成功 **/
    private Boolean locked;

}
