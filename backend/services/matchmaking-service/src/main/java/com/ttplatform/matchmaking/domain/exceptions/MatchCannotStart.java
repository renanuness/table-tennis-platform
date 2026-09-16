package com.ttplatform.matchmaking.domain.exceptions;

public class MatchCannotStart extends RuntimeException{
    public MatchCannotStart(){
        super("match cannot be started");
    }
}
