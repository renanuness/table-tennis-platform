package com.ttplatform.auth.infra.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttplatform.auth.domain.events.Publisher;
import com.ttplatform.auth.domain.events.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class PublisherImpl implements Publisher {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger log = LoggerFactory.getLogger(PublisherImpl.class);

    public PublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void Send(UserCreatedEvent event) {
        try{
            var objectMapper = new ObjectMapper();
            var stringjson = objectMapper.writeValueAsString(event);


            rabbitTemplate.convertAndSend("user.created.exchange","", stringjson);

            log.info("Evento user.created enviado",
                    kv("event_type", "user.created"),
                    kv("user_id", event.id()),
                    kv("source_service", "auth"));
        }catch (Exception  e){

            System.out.println("ERROR PUBLICANDO MENSAGEM");
            System.out.println(e.getMessage());
        }

    }
}
