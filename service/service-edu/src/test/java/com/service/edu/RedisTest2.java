package com.service.edu;

import com.service.base.result.R;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

/**
 * @author alpha
 * @className: RedisTest2
 * @date 2022/7/25 20:59
 * @Description
 */
@SpringBootTest
public class RedisTest2 {
    @Autowired
    RedisTemplate redisTemplate;

    @BeforeEach
    void init() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }
    @Test
    void test(){
        redisTemplate.opsForValue().set("rtobj", R.error(),20, TimeUnit.MINUTES);
        Object rtobj = redisTemplate.opsForValue().get("rtobj");
        System.out.println("rtobj.getClass().getName() = " + rtobj.getClass().getName());
        System.out.println("rtobj = " + rtobj);
    }
}
