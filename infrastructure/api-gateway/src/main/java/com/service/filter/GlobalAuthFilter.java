package com.service.filter;


import com.alibaba.fastjson.JSONObject;
import com.service.base.jwt.JwtHelper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author alpha
 * @className: GlobalAuthFilter
 * @date 2022/8/3 14:25
 * @Description
 */
public class GlobalAuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //获取资源的访问路径
        String path = request.getURI().getPath();
        //ant风格的比较器
        AntPathMatcher matcher = new AntPathMatcher();
        boolean match = matcher.match("api/**/auth/**", path);
        //
        if (!match) {
            //放行请求
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst("token");
        boolean b = JwtHelper.checkToken(token);
        if (b) {
            return chain.filter(exchange);
        }
        //设置响应头
        response.getHeaders().set("Content-Type", "application/json;charset=UTF-8");

        //调用tostring转为json字符串
        JSONObject jsonObject = new JSONObject();
//        jsonObject
        //TODO
        byte[] bs = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
        //将bs报文的字节数组转为缓存数据对象
        DataBuffer data = response.bufferFactory().wrap(bs);
        return response.writeWith(Mono.just(data));
    }

    //值越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}














