package com.ttplatform.auth.domain.model;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;

    private User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this. password = password;
    }


    private User(UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this. password = password;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static User create(String name, String email, String password) {
        return new User(name, email, password);
    }
    public static User populate(UUID id, String name, String email, String password) {
        return new User(id, name, email, password);
    }

    public String getEmail() {
        return this.email;
    }

    public String getName(){
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
}

