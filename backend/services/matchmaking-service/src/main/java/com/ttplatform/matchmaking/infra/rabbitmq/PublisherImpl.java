package com.ttplatform.matchmaking.infra.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttplatform.matchmaking.domain.event.Publisher;
import com.ttplatform.matchmaking.domain.event.MatchFinishedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;

@Service
public class PublisherImpl implements Publisher {

    private final RabbitTemplate rabbitTemplate;

    public PublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void Send(MatchFinishedEvent event) {
        try{
            var objectMapper = new ObjectMapper();
            var stringjson = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend("match.finished", stringjson);
        }catch (Exception  e){

            System.out.println("ERROR PUBLICANDO MENSAGEM");
            System.out.println(e.getMessage());
        }

    }
}
