package com.ttplatform.matchmaking.infra.entity;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

public class GameInfo {
    private Integer gameNumber;
    private Map<String, Short> score;

    public GameInfo(int gameNumber, String player1, String player2, short player1Points, short player2points){
        this.gameNumber = gameNumber;
        score = new Hashtable<>();
        score.put(player1, player1Points);
        score.put(player2, player2points);

    }

    public Short getPlayerPoints(String player){
        return score.get(player);
    }
}
