package com.ttplatform.auth.security;

import com.password4j.Argon2Function;
import com.password4j.Password;
import com.password4j.types.Argon2;

public class PasswordManager {

    public static String hashPassword(String plainTextPassword){
        var myargon2  = Argon2Function.getInstance(1024, 3,2, 32, Argon2.ID, 19);
        var hash = Password.hash(plainTextPassword).addRandomSalt(42).with(myargon2);
        return hash.getResult();
    }
}
