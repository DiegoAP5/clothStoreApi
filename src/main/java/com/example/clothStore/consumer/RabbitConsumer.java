package com.example.clothStore.consumer;


import com.example.clothStore.Notification.RabbitConfigure;
import com.example.clothStore.Notification.RabbitNotification;
import com.example.clothStore.controllers.dtos.requests.CreateOrderRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateSendRequest;
import com.example.clothStore.services.interfaces.IOrderService;
import com.example.clothStore.services.interfaces.ISendService;
import com.example.clothStore.services.interfaces.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitConsumer {

    @Autowired
    private IOrderService service;

    @Autowired
    private ISendService sendService;

    @Autowired
    private IUserService userService;


    @RabbitListener(queues = RabbitConfigure.QUEUE)
    public void notification(@Payload Data data){
        log.info("Received status: {}",data);
        service.create((CreateOrderRequest) data);
        makeSlow();
    }

    @RabbitListener(queues = RabbitNotification.QUEUE)
    public void order(String data) throws JsonProcessingException {
        log.info("Order status: {}",data);
        ObjectMapper objectMapper = new ObjectMapper();
        Long id = Long.valueOf(data.substring(-1,0));
        sendService.update(userService.findUserById(id).getId(),objectMapper.readValue(data, UpdateSendRequest.class));
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
