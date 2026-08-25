package com.ttplatform.auth.domain.events;

public interface Publisher {
    void Send(UserCreatedEvent event);
}
