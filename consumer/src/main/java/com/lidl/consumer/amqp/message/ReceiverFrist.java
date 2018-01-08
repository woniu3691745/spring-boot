package com.lidl.consumer.amqp.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ReceiverFrist {

    private static final Logger logger = Logger.getLogger(ReceiverFrist.class.toString());

    //    @RabbitListener(queues = "test_consume")
    public String onMessageByReturn(@Payload String first) throws InterruptedException {
        logger.info(">>> First 消费信息 " + first + " 成功！");
        return "successful";
    }

    @RabbitListener(queues = "test_consume")
    public void onMessage(@Payload String first) throws InterruptedException {
//        throw new RuntimeException();
        logger.info(">>> First 消费信息 " + first + " 成功！");
//        StopWatch watch = new StopWatch();
//        watch.start();
//        System.out.println("instance " + 1 + " [x] Received '" + foo + "'");
//        doWork(foo);
//        watch.stop();
//        System.out.println("instance " + 1 + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }


    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }

    }
}
