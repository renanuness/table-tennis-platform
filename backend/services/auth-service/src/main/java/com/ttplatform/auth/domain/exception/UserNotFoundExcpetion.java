package com.ttplatform.auth.domain.exception;

public class UserNotFoundExcpetion extends DomainException{
    public UserNotFoundExcpetion() {
        super("Usuário não existente");
    }
}
