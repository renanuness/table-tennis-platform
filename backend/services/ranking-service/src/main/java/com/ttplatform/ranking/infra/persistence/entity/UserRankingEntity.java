package com.ttplatform.ranking.infra.persistence.entity;

import com.ttplatform.ranking.domain.model.UserRanking;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class UserRankingEntity {
    @Id
    private UUID id;

    private int points;
    private int wins;
    private int loss;



    public UserRankingEntity(UUID id, int points, int wins, int loss) {
        this.id = id;
        this.points = points;
        this.wins = wins;
        this.loss = loss;
    }

    public UserRankingEntity() {
    }

    public static UserRankingEntity fromDomain(UserRanking userRanking) {
        return new UserRankingEntity(
                userRanking.getId(),
                userRanking.getPoints(),
                userRanking.getWins(),
                userRanking.getLoss()
        );
    }

    public UserRanking toDomain(){
        return new UserRanking(this.id, this.points, this.wins, this.loss);
    }
}
