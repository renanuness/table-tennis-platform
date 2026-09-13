package com.ttplatform.matchmaking.domain.service;

import com.ttplatform.matchmaking.domain.dto.AnswerInviteDto;
import com.ttplatform.matchmaking.domain.dto.FinishMatchDto;
import com.ttplatform.matchmaking.domain.dto.SendInviteDto;
import com.ttplatform.matchmaking.domain.dto.StartMatchDto;
import com.ttplatform.matchmaking.domain.model.Game;
import com.ttplatform.matchmaking.domain.model.Match;
import com.ttplatform.matchmaking.domain.repository.MatchRepository;
import com.ttplatform.matchmaking.infra.client.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    private final AuthClient authClient;

    public MatchService(MatchRepository matchRepository, AuthClient authClient) {
        this.matchRepository = matchRepository;
        this.authClient = authClient;
    }

    public void sendInvite(SendInviteDto dto){
        // criar match com status pending, player e player 2 invite date
        var player1 = authClient.getById(dto.player1());
        var player2 = authClient.getById(dto.player2());

        var match = Match.FromInvite(dto);
        matchRepository.createMatch(match);
    }

    public void answerInvite(AnswerInviteDto dto){

    }

    public void startMatch(StartMatchDto dto){

    }

    public void finishMatch(FinishMatchDto dto){

    }
}
