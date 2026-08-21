package com.ttplatform.auth.security;

import com.ttplatform.auth.domain.exceptions.InvalidTokenException;
import com.ttplatform.auth.domain.models.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.data.repository.init.ResourceReader;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

public class TokenManager {
    private static String getKey() {
        try (InputStream inputStram = ResourceReader.class.getClassLoader().getResourceAsStream("jwt_key")) {
            if (inputStram == null) {
                throw new IllegalArgumentException("Key file not found");
            }
            String fileContent = new BufferedReader(new InputStreamReader((inputStram))).lines().collect(Collectors.joining("\n"));
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getPrivateKeyFromString(String pemKey) throws Exception {

        String privateKeyPEM = pemKey
                .replace("-----BEGIN OPENSSH PRIVATE KEY-----", "")
                .replace("-----END OPENSSH PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
       return encoded;
    }

    public static String createToken(User user) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            Date exp = new Date(nowMillis + 3600000);

            var builder = Jwts.builder();

            var secretString = getPrivateKeyFromString(getKey());
            SecretKey key = Keys.hmacShaKeyFor(secretString);

            String jwt = builder
                    .subject(user.getId().toString())
                    .issuedAt(now)
                    .expiration(exp)
                    .claim("role", "user")

                    .signWith(key).compact();

            return jwt;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void readToken(String token){
       try {
           var secretString = getPrivateKeyFromString(getKey());
           SecretKey key = Keys.hmacShaKeyFor(secretString);

           Jws<Claims> jws = Jwts.parser()
                   .verifyWith(key)
                   .build()
                   .parseSignedClaims(token);

           //return jws;
       }catch (JwtException e){
           throw new InvalidTokenException();
       }catch (Exception e){
           throw new RuntimeException(e);
       }
    }
}
