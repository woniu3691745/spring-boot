package com.lidl.producer.amqp.msg;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.lidl.common.amqp.comfig.RabbitmqConfig.QUEUE_EXCHANGE_NAME;
import static com.lidl.common.amqp.comfig.RabbitmqConfig.ROUTINGKEY_NAME;

@Component
public class Sender {

    private int dots = 0;
    private int count = 0;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {

//        threadMethod();

        StringBuilder builder = new StringBuilder("Hello");
        if (dots++ == 3) {
            dots = 1;
        }
        for (int i = 0; i < dots; i++) {
            builder.append('.');
        }

        builder.append(Integer.toString(++count));
        String message = builder.toString();
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

//        haveNotCallBack(message, correlationId);
        haveCallBack(message, correlationId);

        confirmCallBack();
        returnCallback();

    }

    /**
     * 发送多条消息
     */
    private void threadMethod() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            executorService.execute(()
                    -> rabbitTemplate.convertAndSend(QUEUE_EXCHANGE_NAME, ROUTINGKEY_NAME, "Hello world MQ !"));
            System.out.println(" >>> " + new Date() + ": " + "生产" + i + "条消息");
        }
        executorService.shutdown();
    }


    /**
     * 没有返回值
     *
     * @param message       发送的消息
     * @param correlationId 消息的标识
     */
    private void haveNotCallBack(String message, CorrelationData correlationId) {
        Map<String, Object> headers = new HashMap<>();
        rabbitTemplate.convertAndSend(QUEUE_EXCHANGE_NAME, ROUTINGKEY_NAME, message, correlationId);
//        rabbitTemplate.convertAndSend(QUEUE_EXCHANGE_NAME, ROUTINGKEY_NAME,
//                new AMQP.BasicProperties()
//                        .builder()
//                        .headers(headers)
//                        .build(),
//                correlationId);
//
//        AMQP.BasicProperties build = new AMQP.BasicProperties()
//                .builder()
//                .headers(headers)
//                .build();
//
//        rabbitTemplate.convertAndSend(QUEUE_EXCHANGE_NAME, ROUTINGKEY_NAME, m -> {
//            m.getMessageProperties().setMessageId("1");
//            m.getMessageProperties().setAppId("1");
//            m.getMessageProperties().setReplyTo("asd");
//            return m;
//        });
    }

    /**
     * 有返回值
     *
     * @param message 发送的消息
     */
    private void haveCallBack(String message,  CorrelationData correlationId) {

        Message message1 = MessageBuilder.withBody("foo".getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId(String.valueOf(new Random().nextInt(1000)))
                .setHeader("bar", "baz-rabbitmq-leo")
                .build();

        String cb = (String) rabbitTemplate.convertSendAndReceive(QUEUE_EXCHANGE_NAME, ROUTINGKEY_NAME, message1, correlationId);
        System.out.println("CallBack -> " + cb);
    }

    /**
     * 判断是否进入exchange
     */
    private void confirmCallBack() {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println(correlationData);
            System.out.println(">>> ack -> " + ack);
            if (ack) {
                System.out.println(">>> ConfirmCallback 消息确认成功");
            } else {
                System.out.println(">>> ConfirmCallback 消息确认失败");
            }
        });
    }

    /**
     * 判断是否进入queue
     * 通过这个routingKey，Exchange无法将消息路由到任何队列，因此导致ReturnCallBack被触发
     */
    private void returnCallback() {
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message1, replyCode, replyText, exchange, routingKey) -> {
            System.out.println(" >>> ReturnCallback " + message1);
        });
    }

    public void A() {
        ConnectionFactory connectionFactory = new CachingConnectionFactory();
        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue("myqueue"));
        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        template.convertAndSend("myqueue", "foo");
        String foo = (String) template.receiveAndConvert("myqueue");
    }
}