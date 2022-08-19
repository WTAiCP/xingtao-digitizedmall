package com.xingtao.xingtaomall.xingtaomallgeteway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.xingtaomallgeteway.config
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/4/29
 */

/**
 * 需要跨域的配置
 */
@Configuration
public class XingtaomallCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        //1、配置跨域
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);

        source.registerCorsConfiguration("/**",corsConfiguration);  //要配置跨域的路径
        return new CorsWebFilter(source);

    }
}
