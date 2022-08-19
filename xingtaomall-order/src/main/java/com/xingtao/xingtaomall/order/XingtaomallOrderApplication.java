package com.xingtao.xingtaomall.order;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@EnableAspectJAutoProxy(exposeProxy = true)
@EnableFeignClients
@EnableRedisHttpSession
@EnableRabbit
@EnableDiscoveryClient
@SpringBootApplication
public class XingtaomallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XingtaomallOrderApplication.class, args);
    }

}
