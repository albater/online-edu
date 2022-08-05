package com.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author alpha
 * @className: GatewayConfig
 * @date 2022/8/3 14:25
 * @Description
 */
@Configuration
public class GatewayConfig {
    @Bean
    public CorsWebFilter corsWebFilter(){

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        //允许所有的请求头跨域访问
        config.addAllowedHeader("*");
        //允许所有的方法跨域访问
        config.addAllowedMethod("*");
        //允许所有的客户端跨域访问
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        //统配所有路径
        configSource.registerCorsConfiguration("/**",config);

        return new CorsWebFilter(configSource);
    }
}
