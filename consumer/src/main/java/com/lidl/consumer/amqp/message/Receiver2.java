package com.lidl.consumer.amqp.message;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class Receiver2 {

    //接收到消息处理.
//    @RabbitListener(queues = "test_consume")
    public void onMessage(@Payload ArrayList foo) {
        System.out.println(">>> " + new Date() + " consumer2 :" + foo);
    }
}
