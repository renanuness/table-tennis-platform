package com.ttplatform.profile.infra.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttplatform.profile.domain.message.CreateUserProfileMessage;
import com.ttplatform.profile.domain.service.ProfileService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final ProfileService service;

    public Consumer(ProfileService service) {
        this.service = service;
    }

    @RabbitListener(queues = "user.created")

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
