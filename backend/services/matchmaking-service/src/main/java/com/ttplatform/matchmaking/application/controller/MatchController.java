package com.ttplatform.matchmaking.application.controller;

import com.ttplatform.matchmaking.domain.dto.*;
import com.ttplatform.matchmaking.domain.service.MatchService;
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

    @PostMapping("/start-match")
    public ResponseEntity startMatch(@RequestBody StartMatchDto dto){
        var match = matchService.startMatch(dto);

        return ResponseEntity.ok(match);
    }

    @PostMapping("/finish-match")
    public ResponseEntity finishMatch(@RequestBody FinishMatchDto dto){
        var match = matchService.finishMatch(dto);

        return ResponseEntity.ok(match);
    }
}
