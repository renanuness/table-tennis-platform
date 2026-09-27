package com.ttplatform.matchmaking.infra.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttplatform.matchmaking.domain.event.Publisher;
import com.ttplatform.matchmaking.domain.event.MatchFinishedEvent;
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

    public void Send(MatchFinishedEvent event) {
        try{
            var objectMapper = new ObjectMapper();
            var stringjson = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend("match.finished", stringjson);

            log.info("Evento match.finished enviado",
                    kv("event_type", "match.finished"),
                    kv("source_service", "matchmaking"));

        }catch (Exception  e){

            System.out.println("ERROR PUBLICANDO MENSAGEM");
            System.out.println(e.getMessage());
        }

    }
}
