package com.ttplatform.ranking.domain.message;

import java.util.UUID;

public record MatchFinishedMessage(UUID winnerId, UUID loserId) {
}
