package com.ttplatform.ranking.domain.model;

import com.ttplatform.ranking.domain.exception.MatchResultInvalidException;

import java.util.UUID;

public class UserRanking {
    private UUID id;
    private int points;
    private int wins;
    private int loss;

    public UserRanking(UUID id, int points, int wins, int loss) {
        this.id = id;
        this.points = points;
        this.wins = wins;
        this.loss = loss;
    }

    public static UserRanking newUser(UUID id){
        return new UserRanking(id, 250, 0 ,0);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public void updatePoints(MatchResult result) {
        if(result == MatchResult.LOSS){
            loss++;
            points -= 8;
            return;
        }else if(result == MatchResult.WIN){
            wins++;
            points += 10;
            return;
        }

        throw new MatchResultInvalidException();
    }
}
