package com.lidl.consumer.amqp.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Component
public class Receiver {

    Logger logger = Logger.getLogger(Receiver.class.toString());

    //接收到消息处理.
    @RabbitListener(queues = "test_consume")
    public void onMessage(@Payload String foo) throws InterruptedException {
        logger.info(">>> 消费信息 " + foo + " 成功！");

//        StopWatch watch = new StopWatch();
//        watch.start();
//        System.out.println("instance " + 1 + " [x] Received '" + foo + "'");
//        doWork(foo);
//        watch.stop();
//        System.out.println("instance " + 1 + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

//    private void doWork(String in) throws InterruptedException {
//        for (char ch : in.toCharArray()) {
//            if (ch == '.') {
//                Thread.sleep(1000);
//            }
//        }
//    }

}
