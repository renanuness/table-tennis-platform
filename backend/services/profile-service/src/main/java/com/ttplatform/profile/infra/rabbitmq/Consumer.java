package com.ttplatform.profile.infra.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @RabbitListener(queues = "user.created")
    public void listen(String message){
        System.out.println("Received <" + message + ">");
    }
    public void receiveMessage(String message){
        System.out.println("Received <" + message + ">");
    }
}
