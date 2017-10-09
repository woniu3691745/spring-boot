package com.lidl.consumer.amqp.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * consumer 配置文件
 */
@Configuration
public class Config {

    private static final String QUEUE_EXCHANGE_NAME = "test_exchange";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(QUEUE_EXCHANGE_NAME);
    }
}
