package com.xingtao.xingtaomall.xingtaomallcart.to;

import lombok.Data;
import lombok.ToString;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-06-30 17:35
 **/
@ToString
@Data
public class UserInfoTo {

    private Long userId;  //登录后才会有的userId

    private String userKey;  //不管有没有登录，都会有临时userKey  一定有userKey

    /**
     * 是否临时用户  标志位
     */
    private Boolean tempUser = false;

}
