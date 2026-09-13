package com.ttplatform.auth.domain.events;

import java.util.UUID;

public record UserCreatedEvent(UUID id, String name, String email) {
}
