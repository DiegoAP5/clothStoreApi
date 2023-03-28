package com.example.clothStore.consumer;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitConsumer {

    @RabbitListener(queues = {"${sacavix.queue.name.notification}"})
    public void notification(@Payload Data data){
        log.info("Notification send: {}",data);

        makeSlow();
    }

    @RabbitListener(queues = {"${sacavix.queue.name.cart}"})
    public void order(@Payload Data data){
        log.info("Cart status: {}",data);

        makeSlow();
    }

    @RabbitListener(queues = {"${spring.rabbitmq.queue.name.notification}"})
    public void shipping(@Payload Data data){
        log.info("Shipping status: {}",data);

        makeSlow();
    }

    @RabbitListener(queues = {"${sacavix.queue.name.refund}"})
    public void refund(@Payload Data data){
        log.info("Refund status: {}",data);

        makeSlow();
    }

    private void makeSlow() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
