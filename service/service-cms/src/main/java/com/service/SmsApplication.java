package com.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author alpha
 * @className: SmsApplication
 * @date 2022/8/1 10:44
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.service")
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class,args);
    }
}
