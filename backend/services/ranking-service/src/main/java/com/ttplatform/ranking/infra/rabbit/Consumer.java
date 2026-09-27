package com.ttplatform.ranking.infra.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttplatform.ranking.domain.message.MatchFinishedMessage;
import com.ttplatform.ranking.domain.message.UserCreatedMessage;
import com.ttplatform.ranking.domain.model.MatchResult;
import com.ttplatform.ranking.domain.service.RankingService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
public class Consumer {

    private final RankingService service;

    public Consumer(RankingService service) {
        this.service = service;
    }

    @RabbitListener(queues = "match.finished")
    public void matchFinishedListener(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MatchFinishedMessage matchFinishedMessage = objectMapper.readValue(message, MatchFinishedMessage.class);
            service.updateUsersRanking(matchFinishedMessage.winnerId(), matchFinishedMessage.loserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "ranking.user.created.queue", durable = "true"),
            exchange = @Exchange(name = "user.created.exchange", type = ExchangeTypes.FANOUT)
    ))
    public void userCreatedListener(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserCreatedMessage userCreatedMessage = objectMapper.readValue(message, UserCreatedMessage.class);
            service.createUserRanking(userCreatedMessage.id());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
