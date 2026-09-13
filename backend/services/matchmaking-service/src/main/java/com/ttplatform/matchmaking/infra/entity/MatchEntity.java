package com.ttplatform.matchmaking.infra.entity;

import com.ttplatform.matchmaking.domain.model.MatchStatus;
import com.ttplatform.matchmaking.domain.model.MatchType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "matches")
public class MatchEntity {

    @Id
    private String id;

    private String tournamentId;

    @Enumerated(EnumType.STRING)
    private MatchType matchType; // SINGLES, DOUBLES, EXHIBITION

    @Enumerated(EnumType.STRING)
    private MatchStatus status; // SCHEDULED, IN_PROGRESS, FINISHED, CANCELLED

    // Flexível: aceita 1 ou mais jogadores
    private List<PlayerInfo> players;

    // Para duplas
    private List<TeamInfo> teams;

    // Sets com games aninhados
    private List<SetInfo> sets;

    private String matchWinner;

    private Instant startedAt;
    private Instant endedAt;
    private Instant createdAt;
}

