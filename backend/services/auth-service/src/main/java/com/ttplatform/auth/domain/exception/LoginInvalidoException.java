package com.ttplatform.auth.domain.exception;

public class LoginInvalidoException extends DomainException{
    public LoginInvalidoException() {
        super("Login inválido.");
    }
}
