package com.lidl.consumer.amqp.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ReceiverSecond {

    private static final Logger logger = Logger.getLogger(ReceiverSecond.class.toString());

    /**
     * 接收到消息处理.
     * 如果抛出异常，消息主动被另一个消费者消费
     *
     * @param second 接受的消息
     */
    @RabbitListener(queues = "test_consume")
    public void onMessage(@Payload String second) {
//        throw new RuntimeException();
        logger.info(">>> Second 消费信息 " + second + " 成功！");
    }
}
