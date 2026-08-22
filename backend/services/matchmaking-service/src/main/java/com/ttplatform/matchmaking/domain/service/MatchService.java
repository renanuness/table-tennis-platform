package com.ttplatform.matchmaking.domain.service;

import com.ttplatform.matchmaking.domain.dto.AnswerInviteDto;
import com.ttplatform.matchmaking.domain.dto.FinishMatchDto;
import com.ttplatform.matchmaking.domain.dto.SendInviteDto;
import com.ttplatform.matchmaking.domain.dto.StartMatchDto;
import com.ttplatform.matchmaking.domain.model.Game;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MatchService {



    public void sendInvite(SendInviteDto dto){
        // criar match com status pending, player e player 2 invite date

    }

    public void answerInvite(AnswerInviteDto dto){

    }

    public void startMatch(StartMatchDto dto){

    }

    public void finishMatch(FinishMatchDto dto){

    }
}
