package com.ttplatform.matchmaking.domain.exceptions;

public class InviteCannotBeAnswered extends RuntimeException{
    public InviteCannotBeAnswered(){
        super("Invite cannot be answered");
    }
}
