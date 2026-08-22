package com.ttplatform.matchmaking.domain.dto;

import java.util.UUID;

public record AnswerInviteDto(UUID matchId, boolean answer) {
}
