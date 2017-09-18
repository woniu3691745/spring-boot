package com.lidl.productor.amqp.msg;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.lidl.common.amqp.comfig.RabbitmqConfig.QUEUE_EXCHANGE_NAME;
import static com.lidl.common.amqp.comfig.RabbitmqConfig.ROUTINGKEY_NAME;

@Component
public class Sender {

    private int dots = 0;
    private int count = 0;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 10; i++) {
//            executorService.execute(()
//                    -> rabbitTemplate.convertAndSend(QUEUE_EXCHANGE_NAME, ROUTINGKEY_NAME, "Hello world MQ !"));
//            System.out.println(" >>> " + new Date() + ": " + "生产" + i + "条消息");
//        }
//        System.out.println(">>> ----------------------------");
//        executorService.shutdown();

        StringBuilder builder = new StringBuilder("Hello");
        if (dots++ == 3) {
            dots = 1;
        }
        for (int i = 0; i < dots; i++) {
            builder.append('.');
        }

        builder.append(Integer.toString(++count));
        String message = builder.toString();
        rabbitTemplate.convertAndSend(QUEUE_EXCHANGE_NAME, ROUTINGKEY_NAME, message);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack){
                System.out.println("> 消息确认成功");
            }else{
                System.out.println("> 消息确认失败");
            }
        });
        rabbitTemplate.setReturnCallback((message1, replyCode, replyText, exchange, routingKey) -> {
            System.out.println(" >>> callback " + message1);
        });
        System.out.println(" >>> Sent " + message + "'");
    }
}
