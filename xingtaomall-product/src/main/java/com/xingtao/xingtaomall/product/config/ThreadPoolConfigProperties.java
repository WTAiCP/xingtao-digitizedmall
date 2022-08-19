package com.xingtao.xingtaomall.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.product.config
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/6/2
 */
@ConfigurationProperties(prefix = "xingtaomall.thread")
@Component
@Data
public class ThreadPoolConfigProperties {
    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;
}
