package com.lidl.consumer.amqp.message;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class ReceiverFrist {


    private static final Logger logger = Logger.getLogger(ReceiverFrist.class.toString());

    @RabbitListener(queues = "test_consume")
    public String onMessageByReturn(@Payload String first, @Header("bar") String rk, Message message) throws InterruptedException {
        System.out.println(">>> message.getMessageProperties() " + message.getMessageProperties());
        logger.info(">>> First 消费信息 " + first + " 成功！");
        logger.info(">>> rk " + rk);
        return "First Successful";
    }

    //    @RabbitListener(queues = "test_consume")
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

//    @RabbitListener(queues = "test_consume")
    public void onMessage1(@Payload String thread, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        try {
            channel.basicAck(deliveryTag, false);
            // ack返回false，并重新回到队列
//            channel.basicNack(deliveryTag, false, true);
//            channel.basicReject(deliveryTag, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }

    }
}
