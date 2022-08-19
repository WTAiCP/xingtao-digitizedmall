package com.xingtao.xingtaomall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@MapperScan("com.xingtao.xingtaomall.coupon.dao")
@SpringBootApplication
public class XingtaomallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(XingtaomallCouponApplication.class, args);
    }

}
