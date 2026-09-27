package com.ttplatform.matchmaking.domain.exceptions;

public class NotAllowed extends RuntimeException {
    public NotAllowed(){
        super("Operation not allowed");
    }
}
