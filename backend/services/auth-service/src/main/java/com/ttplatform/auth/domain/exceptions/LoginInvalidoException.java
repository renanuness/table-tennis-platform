package com.ttplatform.auth.domain.exceptions;

public class LoginInvalidoException extends DomainException{
    public LoginInvalidoException() {
        super("Login inválido.");
    }
}
