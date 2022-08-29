package com.service.trade.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import javax.annotation.PostConstruct;

/**
 * @author alpha
 * @className: RabbitmqConfig
 * @date 2022/8/5 21:20
 * @Description
 */
@Configuration
@Slf4j
public class RabbitmqConfig {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @PostConstruct
    public void init(){
        //消息是否到达交换机的回调
        rabbitTemplate.setConfirmCallback((@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause)->{
            if (!ack){
                log.error("消息未到达交换机：{}",cause);
            }
        });
        //消息未到达队列的回调
        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText, String exchange, String routingKey)->{
            log.error("消息未到达队列：message = {},replyCode = {},replyText = {},exchange = {},routingKey = {}",
                    new String(message.getBody()),replyCode,replyText,exchange,routingKey);
        });
    }
}
