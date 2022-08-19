package com.xingtao.xingtaomall.xingtaomallcart.config;

import com.xingtao.xingtaomall.xingtaomallcart.interceptor.CartInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description   配置拦截器
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.xingtaomallcart.config
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/6/6
 */
public class XingtaomallWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CartInterceptor()).addPathPatterns("/**");  //  "/**"拦截所有请求
    }
}
