package com.xingtao.xingtaomall.ware.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-03 20:07
 **/

@Data
public class FareVo {

    private MemberAddressVo address;

    private BigDecimal fare;

}


