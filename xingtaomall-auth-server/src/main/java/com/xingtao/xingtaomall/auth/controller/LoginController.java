package com.xingtao.xingtaomall.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.auth.controller
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/6/4
 */
@Controller
public class LoginController {

    @GetMapping(value = "/login.html")
    public String loginPage(HttpSession session) {

        //从session先取出来用户的信息，判断用户是否已经登录过了
        //Object attribute = session.getAttribute(LOGIN_USER);
        //如果用户没登录那就跳转到登录页面
        //if (attribute == null) {
            return "login";
        //} else {
            //return "redirect:http://gulimall.com";
        //}

    }
}
