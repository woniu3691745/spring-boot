package com.lidl.consumer.amqp.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
public class ReceiverSecond {

    private static final Logger logger = Logger.getLogger(ReceiverSecond.class.toString());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 接收到消息处理.
     * 如果抛出异常，消息主动被另一个消费者消费
     *
     * @param second 接受的消息
     */
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "test_consume_1", durable = "true"),
//            exchange = @Exchange(value = "test_exchange_1", ignoreDeclarationExceptions = "true"),
//            key = "testRoutingKey_1")
//    )
    @RabbitListener(queues = "test_consume")
    public String onMessage(@Payload String second) {
//        throw new RuntimeException();
        try {
            TimeUnit.SECONDS.sleep(2L);
            logger.info(">>> Second 消费信息 " + second + " 成功！");
            return "delay";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
