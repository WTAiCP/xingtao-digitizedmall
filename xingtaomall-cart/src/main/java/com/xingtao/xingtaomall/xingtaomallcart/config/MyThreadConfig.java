package com.xingtao.xingtaomall.xingtaomallcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.product.config
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/6/2
 */
//@EnableConfigurationProperties(ThreadPoolConfigProperties.class) ThreadPoolConfigProperties已@Component放到了容器中了
@Configuration
public class MyThreadConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties pool) {
        return new ThreadPoolExecutor(
                pool.getCoreSize(),
                pool.getMaxSize(),
                pool.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
