package com.xingtao.xingtaomall.xingtaomallgeteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1、开启注册发现
 * (配置nacos注册中心地址)
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) //gateway没有用到数据源，排除掉相关配置
public class XingtaomallGetewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(XingtaomallGetewayApplication.class, args);
    }

}
