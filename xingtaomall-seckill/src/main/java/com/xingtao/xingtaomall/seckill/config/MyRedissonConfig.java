package com.xingtao.xingtaomall.seckill.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Description 程序化配置  若使用文件化配置请查看文档
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.product.config
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/5/23
 */
@Configuration
public class MyRedissonConfig {

    /**
     * 程序化配置：
     * 所有对redisson的使用都有通过RedissonClinent
     * 单点模式，非集群模式
     * 查文档
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson() throws IOException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.66.100:6379");
        RedissonClient redissonClient = Redisson.create(config);
        long[] l = new long[5];
        return redissonClient;
    }
}
