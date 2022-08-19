package com.xingtao.xingtaomall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-04 23:19
 **/

@Data
public class FareVo {

    private MemberAddressVo address;

    private BigDecimal fare;

}
