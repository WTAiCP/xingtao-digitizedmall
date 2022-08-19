package com.xingtao.xingtaomall.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: springMVC适配器——不必在controller中填写空方法，只为了返回一个页面
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-06-27 08:52
 **/
@Configuration
public class XingtaollmallWebConfig implements WebMvcConfigurer {

    /**·
     * 视图映射:发送一个请求，直接跳转到一个页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/reg.html").setViewName("reg");
    }
}
