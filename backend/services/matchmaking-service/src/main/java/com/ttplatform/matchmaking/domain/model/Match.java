package com.ttplatform.matchmaking.domain.model;

import com.ttplatform.matchmaking.domain.dto.SendInviteDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Match {
    private UUID id;
    private MatchStatus matchStatus;
    private MatchType matchType;
    private EventType eventType;
    private List<Game> games;
    private UUID player1;
    private UUID player2;
    //private UUID double1;
    //private UUID double2;
    //private Place place;
    private LocalDateTime date;

    public Match(UUID player1, UUID player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.eventType = EventType.SINGLE_GAME;
        this.matchStatus = MatchStatus.INVITE_PENDING;
    }

    public static Match FromInvite(SendInviteDto dto){
        return new Match(dto.player1(), dto.player2());
    }


}

/*
EventType
    Single Game
    Tournament
 */