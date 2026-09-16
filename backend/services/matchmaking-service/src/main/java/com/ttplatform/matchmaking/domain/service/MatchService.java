package com.ttplatform.matchmaking.domain.service;

import com.ttplatform.matchmaking.domain.dto.AnswerInviteDto;
import com.ttplatform.matchmaking.domain.dto.FinishMatchDto;
import com.ttplatform.matchmaking.domain.dto.SendInviteDto;
import com.ttplatform.matchmaking.domain.dto.StartMatchDto;
import com.ttplatform.matchmaking.domain.exceptions.MatchNotFoundException;
import com.ttplatform.matchmaking.domain.exceptions.PlayerNotFoundException;
import com.ttplatform.matchmaking.domain.model.Match;
import com.ttplatform.matchmaking.domain.repository.MatchRepository;
import com.ttplatform.matchmaking.infra.client.AuthClient;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    private final AuthClient authClient;

    public MatchService(MatchRepository matchRepository, AuthClient authClient) {
        this.matchRepository = matchRepository;
        this.authClient = authClient;
    }

    public Match sendInvite(SendInviteDto dto){
        // criar match com status pending, player e player 2 invite date
        var player1 = authClient.getById(dto.player1());
        var player2 = authClient.getById(dto.player2());

        if(player1 == null || player2 == null){
            throw new PlayerNotFoundException();
        }
        var match = Match.FromInvite(dto);
        return matchRepository.createMatch(match);
    }

    public void answerInvite(AnswerInviteDto dto){
        var match = matchRepository.getById(dto.matchId()).orElseThrow(MatchNotFoundException::new);

        if(dto.answer()){
            match.acceptInvite();
        }else{
            match.denyInvite();
        }

        matchRepository.updateMatch(match);
    }

    public Match startMatch(StartMatchDto dto){
        var match = matchRepository.getById(dto.matchId()).orElseThrow(MatchNotFoundException::new);

        match.starMatch();

        var updatedMatch = matchRepository.updateMatch(match);

        return updatedMatch;
    }

    public Match finishMatch(FinishMatchDto dto){

    }

    public List<Match> getUserPendingMatches(UUID userId) {
        var matches = matchRepository.getUserPendingMatches(userId);

        return matches;
    }
}
