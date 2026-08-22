package com.ttplatform.matchmaking.domain.dto;

import com.ttplatform.matchmaking.domain.model.Game;

import java.util.List;
import java.util.UUID;

public record FinishMatchDto(UUID matchId, UUID playerId, List<Game> games) {
}
