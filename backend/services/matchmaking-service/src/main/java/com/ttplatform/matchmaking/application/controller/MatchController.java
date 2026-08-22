package com.ttplatform.matchmaking.application.controller;

import com.ttplatform.matchmaking.application.dtos.InviteRequest;
import com.ttplatform.matchmaking.domain.dto.SendInviteDto;
import com.ttplatform.matchmaking.domain.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/send-invite")
    public ResponseEntity sendInvite(@RequestBody InviteRequest request){
        matchService.sendInvite(new SendInviteDto(request.player1(), request.player2()));
        return ResponseEntity.ok("OK");
    }
}
