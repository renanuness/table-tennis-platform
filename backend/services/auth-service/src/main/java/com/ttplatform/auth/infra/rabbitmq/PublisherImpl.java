package com.ttplatform.auth.infra.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttplatform.auth.domain.events.Publisher;
import com.ttplatform.auth.domain.events.UserCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;

@Service
public class PublisherImpl implements Publisher {

    private final RabbitTemplate rabbitTemplate;

    public PublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void Send(UserCreatedEvent event) {
        try{
            var objectMapper = new ObjectMapper();
            var stringjson = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend("user.created", stringjson);
        }catch (Exception  e){

            System.out.println("ERROR PUBLICANDO MENSAGEM");
            System.out.println(e.getMessage());
        }

    }
}
