package com.ttplatform.matchmaking.domain.dto;

import java.util.UUID;

public record StartMatchDto(UUID matchId, UUID playerId) {
}
