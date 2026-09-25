package com.ttplatform.profile.infra.persistence.entity;

import com.ttplatform.profile.domain.model.UserProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class UserProfileEntity {
    @Id
    private UUID id;

    private String name;
    private String email;



    public UserProfileEntity(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserProfileEntity() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }


    public static UserProfileEntity fromDomain(UserProfile userProfile){
        return new UserProfileEntity(
                userProfile.getId(),
                userProfile.getName(),
                userProfile.getEmail()
        );
    }
}
