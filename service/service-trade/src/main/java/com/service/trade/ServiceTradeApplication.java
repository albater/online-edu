package com.service.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author alpha
 * @className: ServiceTradeApplication
 * @date 2022/8/5 19:11
 * @Description
 */
@SpringBootApplication
@ComponentScan("com.service")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceTradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceTradeApplication.class, args);
    }
}
