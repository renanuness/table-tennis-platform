package com.ttplatform.profile.infra.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttplatform.profile.domain.message.CreateUserProfileMessage;
import com.ttplatform.profile.domain.service.ProfileService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Consumer {
    private final ProfileService service;

    public Consumer(ProfileService service) {
        this.service = service;
    }

    @RabbitListener(queues = "profile.user.created.queue")
    public void listen(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CreateUserProfileMessage createUserProfileMessage = objectMapper.readValue(message, CreateUserProfileMessage.class);
            service.createUserProfile(createUserProfileMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
