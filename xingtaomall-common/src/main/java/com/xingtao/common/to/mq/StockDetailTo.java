package com.xingtao.common.to.mq;

import lombok.Data;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-06 21:14
 **/

@Data
public class StockDetailTo {

    private Long id;
    /**
     * sku_id
     */
    private Long skuId;
    /**
     * sku_name
     */
    private String skuName;
    /**
     * 购买个数
     */
    private Integer skuNum;
    /**
     * 工作单id
     */
    private Long taskId;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 锁定状态
     */
    private Integer lockStatus;

}
