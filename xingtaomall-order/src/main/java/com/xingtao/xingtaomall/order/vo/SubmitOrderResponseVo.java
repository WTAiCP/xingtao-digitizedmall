package com.xingtao.xingtaomall.order.vo;


import com.xingtao.xingtaomall.order.entity.OrderEntity;
import lombok.Data;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-04 22:34
 **/

@Data
public class SubmitOrderResponseVo {

    private OrderEntity order;

    /** 错误状态码 **/
    private Integer code;


}
