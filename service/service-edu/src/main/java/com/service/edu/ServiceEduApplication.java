package com.service.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author alpha
 * @className: ServiceEduApplication
 * @date 2022/7/17 22:57
 * @Description
 */
@SpringBootApplication
@MapperScan("com.service.edu.mapper")
public class ServiceEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class,args);
    }
}
