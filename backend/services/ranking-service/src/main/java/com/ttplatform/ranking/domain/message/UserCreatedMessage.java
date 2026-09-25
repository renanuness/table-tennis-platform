package com.ttplatform.ranking.domain.message;

import java.util.UUID;

public record UserCreatedMessage(UUID id, String name, String email) {
}
