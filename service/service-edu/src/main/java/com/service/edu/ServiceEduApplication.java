package com.service.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author alpha
 * @className: ServiceEduApplication
 * @date 2022/7/17 22:57
 * @Description
 */
@SpringBootApplication
@MapperScan("com.service.edu.mapper")
@ComponentScan("com.service")
@EnableDiscoveryClient //开启nacos
@EnableFeignClients //开启feign
@EnableScheduling //开启定时任务
public class ServiceEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class,args);
    }
}
