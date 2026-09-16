package com.ttplatform.matchmaking.application.controller;

import com.ttplatform.matchmaking.domain.dto.AnswerInviteDto;
import com.ttplatform.matchmaking.domain.dto.SendInviteDto;
import com.ttplatform.matchmaking.domain.service.MatchService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    //DON
    @PostMapping("/send-invite")
    public ResponseEntity sendInvite(@RequestBody SendInviteDto dto){
        var match = matchService.sendInvite(dto);
        return ResponseEntity.ok(match);
    }

    @PostMapping("/answer-invite")
    public ResponseEntity answerInvite(@RequestBody AnswerInviteDto dto){
        matchService.answerInvite(dto);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/pending-games/{userId}")
    public ResponseEntity getUserPendingGames(@PathVariable UUID userId){
        var matches = matchService.getUserPendingMatches(userId);

        return ResponseEntity.ok(matches);
    }

    @PostMapping("/start-match/{matchId}")
    public ResponseEntity startMatch(@PathVariable UUID matchId){
        var match = matchService.startMatch(matchId);

        return ResponseEntity.ok(match);
    }
}
