package com.xingtao.xingtaomall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableRabbit
@MapperScan("com.xingtao.xingtaomall.ware.dao")
@EnableFeignClients
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
public class XingtaomallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(XingtaomallWareApplication.class, args);
    }

}
