package com.ttplatform.matchmaking.domain.exceptions;

public class InvalidMatchScore extends RuntimeException{
    public InvalidMatchScore(){
        super("Invalid match score");
    }
}
