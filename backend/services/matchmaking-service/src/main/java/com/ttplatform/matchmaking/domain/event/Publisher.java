package com.ttplatform.matchmaking.domain.event;

public interface Publisher {
    void Send(MatchFinishedEvent event);
}
