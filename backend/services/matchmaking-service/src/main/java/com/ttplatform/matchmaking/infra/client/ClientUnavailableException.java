package com.ttplatform.matchmaking.infra.client;

public class ClientUnavailableException extends RuntimeException{
    public ClientUnavailableException(String message) {
        super(message);
    }
}
