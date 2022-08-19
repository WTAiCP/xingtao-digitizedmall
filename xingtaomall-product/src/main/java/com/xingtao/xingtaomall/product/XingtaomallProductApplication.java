package com.xingtao.xingtaomall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.xingtao.xingtaomall.product.feign")
@EnableDiscoveryClient
@MapperScan("com.xingtao.xingtaomall.product.dao")
@SpringBootApplication
public class XingtaomallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(XingtaomallProductApplication.class, args);
    }

}
