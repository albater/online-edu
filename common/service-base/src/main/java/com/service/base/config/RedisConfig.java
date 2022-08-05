package com.service.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;

/**
 * @author alpha
 * @className: RedisConfig
 * @date 2022/8/2 10:22
 * @Description
 */
@Configuration
public class RedisConfig {
    @Autowired
    RedisTemplate redisTemplate;

    //依赖注入完之后直接初始化的方法
    @PostConstruct
    public void init(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }
}
