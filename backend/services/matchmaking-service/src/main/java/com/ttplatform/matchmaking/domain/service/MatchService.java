package com.ttplatform.matchmaking.domain.service;

import com.ttplatform.matchmaking.domain.dto.AnswerInviteDto;
import com.ttplatform.matchmaking.domain.dto.FinishMatchDto;
import com.ttplatform.matchmaking.domain.dto.SendInviteDto;
import com.ttplatform.matchmaking.domain.dto.StartMatchDto;
import com.ttplatform.matchmaking.domain.event.MatchFinishedEvent;
import com.ttplatform.matchmaking.domain.event.Publisher;
import com.ttplatform.matchmaking.domain.exceptions.MatchNotFoundException;
import com.ttplatform.matchmaking.domain.exceptions.PlayerNotFoundException;
import com.ttplatform.matchmaking.domain.model.Match;
import com.ttplatform.matchmaking.domain.repository.MatchRepository;
import com.ttplatform.matchmaking.infra.client.AuthClient;
import com.ttplatform.matchmaking.infra.rabbitmq.PublisherImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class MatchService {
    private static final Logger log = LoggerFactory.getLogger(PublisherImpl.class);
    private final MatchRepository matchRepository;
    private final AuthClient authClient;
    private final Publisher publisher;

    public MatchService(MatchRepository matchRepository, AuthClient authClient, Publisher publisher) {
        this.matchRepository = matchRepository;
        this.authClient = authClient;
        this.publisher = publisher;
    }

    public Match sendInvite(SendInviteDto dto){
        // criar match com status pending, player e player 2 invite date
        var player1 = authClient.getById(dto.player1());
        var player2 = authClient.getById(dto.player2());

        if(player1 == null || player2 == null){
            throw new PlayerNotFoundException();
        }
        var match = Match.FromInvite(player1, player2);

        log.info("Match created",
                kv("source_service", "matchmaking"));
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


        match.starMatch(dto.playerId());

        var updatedMatch = matchRepository.updateMatch(match);

        log.info("Match started",
                kv("source_service", "matchmaking"));

        return updatedMatch;
    }

    public Match finishMatch(FinishMatchDto dto){
        var match = matchRepository.getById(dto.matchId()).orElseThrow(MatchNotFoundException::new);

        match.finishMatch(dto.playerId(), dto.games());

        var matchUpdated = matchRepository.updateMatch(match);

        //PUBLISH EVENT OF MATCH FINISHED
        var loser = match.getWinner() == match.getPlayer1() ? match.getPlayer2() : match.getPlayer1();
        publisher.Send(new MatchFinishedEvent(match.getWinner(), loser));

        log.info("Match finished",
                kv("source_service", "matchmaking"));

        return matchUpdated;
    }

    public List<Match> getUserPendingMatches(UUID userId) {
        var matches = matchRepository.getUserPendingMatches(userId);

        return matches;
    }
}
