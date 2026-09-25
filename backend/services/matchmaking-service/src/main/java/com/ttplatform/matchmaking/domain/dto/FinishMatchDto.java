package com.ttplatform.matchmaking.domain.dto;

import com.ttplatform.matchmaking.domain.model.Game;
import com.ttplatform.matchmaking.infra.entity.GameInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record FinishMatchDto(UUID matchId, UUID playerId, List<Game> games) {

}
