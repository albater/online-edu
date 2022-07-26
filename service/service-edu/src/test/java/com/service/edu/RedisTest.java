package com.service.edu;

import com.google.gson.Gson;
import com.service.base.result.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author alpha
 * @className: RedisTest
 * @date 2022/7/25 20:06
 * @Description
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void test1() {
//        stringRedisTemplate.opsForValue().set("srtKey","value");
//        System.out.println("stringRedisTemplate.hasKey(\"srtKey\") = " + stringRedisTemplate.hasKey("srtKey"));
        //设置过期时间
//        stringRedisTemplate.opsForValue().set("srtkey2","value2",5, TimeUnit.MINUTES);
//        System.out.println("stringRedisTemplate.getExpire(\"setkey2\",TimeUnit.MINUTES) = " + stringRedisTemplate.getExpire("setkey2", TimeUnit.MINUTES));
        Gson gson = new Gson();
        stringRedisTemplate.opsForValue().set("srtobj",gson.toJson(R.ok()));
        R r2 = gson.fromJson(stringRedisTemplate.opsForValue().get("srtObj"), R.class);
        System.out.println(r2);
    }

    @Test
    void test(){
        stringRedisTemplate.opsForValue().set("srtKey","hehe");
        System.out.println("stringRedisTemplate.hasKey(\"srtKey\") = " + stringRedisTemplate.hasKey("srtKey"));
        stringRedisTemplate.opsForValue().set("srtKey2" , "haha",10, TimeUnit.MINUTES);//设置过期时间
        System.out.println("stringRedisTemplate.getExpire(\"srtKey2\",TimeUnit.SECONDS) = " + stringRedisTemplate.getExpire("srtKey2", TimeUnit.SECONDS));
        Gson gson = new Gson();
        stringRedisTemplate.opsForValue().set("srtObj" , gson.toJson(R.ok()));

        R r2 = gson.fromJson(stringRedisTemplate.opsForValue().get("srtObj"), R.class);
        System.out.println(r2);
        System.out.println("=======================================================");
        //redisTemplate保存的对象如果是系统类对象，系统类已经实现了序列化接口
        // 存入的对象在redis中不可读
        //如果我们希望redisTemplate能够像StringRedisTemplate一样自动将对象转为json字符串自动存到redis，读取时自动将字符串转为java对象
        //redisTemplate:是以序列化的形式将对象存到redis，需要自定义类型的对象实现序列化接口
        redisTemplate.opsForValue().set("rtObj" , R.error(),20,TimeUnit.MINUTES);
        Object obj = redisTemplate.opsForValue().get("rtObj");
        System.out.println(obj);
        System.out.println(obj.getClass().getName());


    }


}
