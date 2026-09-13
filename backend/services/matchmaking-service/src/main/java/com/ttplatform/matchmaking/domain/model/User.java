package com.ttplatform.matchmaking.domain.model;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;

    private User(String name, String email) {
        this.name = name;
        this.email = email;
    }


    private User(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static User create(String name, String email ) {
        return new User(name, email);
    }

    public String getEmail() {
        return this.email;
    }

    public String getName(){
        return this.name;
    }

}