package com.ttplatform.profile.infra.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitConfiguration {

    //private static final String userCreatedExchane = "user.created.exchange";

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("rabbitmq");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("rabbitmq");
        connectionFactory.setPassword("rabbitmq");
        return connectionFactory;
    }

    @Bean
    public FanoutExchange userCreatedExchange() {
        return new FanoutExchange("user.created.exchange");
    }
    
    @Bean
    public Queue profileQueue() {
        return new Queue("profile.user.created.queue", true); // Fila durável
    }

    @Bean
    public Binding profileBinding(Queue profileQueue, FanoutExchange userCreatedExchange) {
        return BindingBuilder.bind(profileQueue).to(userCreatedExchange);
    }
}
