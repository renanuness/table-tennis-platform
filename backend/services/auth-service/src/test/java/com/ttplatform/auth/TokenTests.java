package com.ttplatform.auth;

import com.ttplatform.auth.domain.model.User;
import com.ttplatform.auth.security.TokenManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TokenTests {

    @Test
    @DisplayName("Test JWT creation")
    void token_creation_shouldSucced(){
        var user = User.create("Test", "test@mail.com", "SenhaSegura@123");

        var token = TokenManager.createToken(user);

        assertNotNull(token);
        assertDoesNotThrow(()->TokenManager.readToken(token));
    }
}
