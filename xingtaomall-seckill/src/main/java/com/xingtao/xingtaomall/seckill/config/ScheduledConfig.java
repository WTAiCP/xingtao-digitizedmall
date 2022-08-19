package com.xingtao.xingtaomall.seckill.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description: 开启@EnableAsync和@EnableScheduling，scheduled就不用开启了
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-09 19:23
 **/

@EnableAsync
@EnableScheduling
@Configuration
public class ScheduledConfig {

}
