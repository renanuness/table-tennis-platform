package com.ttplatform.matchmaking.domain.model;

import java.util.UUID;
import com.ttplatform.matchmaking.domain.exceptions.InvalidMatchScore;

public class Game{
    private short player1Points;
    private short player2Points;

    public Game(short player1Points, short player2Points) {
        this.player1Points = player1Points;
        this.player2Points = player2Points;
    }

    public short getPlayer1Points() {
        return player1Points;
    }

    public void setPlayer1Points(short player1Points) {
        this.player1Points = player1Points;
    }

    public short getPlayer2Points() {
        return player2Points;
    }

    public void setPlayer2Points(short player2Points) {
        this.player2Points = player2Points;
    }

    public boolean isValid(){
        if((Math.abs(player1Points - player2Points) < 2) || (player1Points < 11 && player2Points < 11 )){
            return false;
        }

        return true;
    }
}
