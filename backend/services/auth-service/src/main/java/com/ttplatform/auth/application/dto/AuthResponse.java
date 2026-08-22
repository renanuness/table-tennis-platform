package com.ttplatform.auth.application.dto;

import com.ttplatform.auth.domain.model.User;public class AuthResponse {
    private String token;
    //private String refreshToken;
    private String email;
    private String name;

    public AuthResponse(User user, String token) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.token = token;
    }

    // Getters e Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    //public String getRefreshToken() { return refreshToken; }
    //public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
