package com.ttplatform.matchmaking.domain.event;

import java.util.UUID;

public record MatchFinishedEvent(UUID winnerId, UUID loserId) {

}
