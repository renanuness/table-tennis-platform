package com.ttplatform.matchmaking.domain.model;

import com.ttplatform.matchmaking.domain.dto.SendInviteDto;
import com.ttplatform.matchmaking.domain.exceptions.InvalidMatchScore;
import com.ttplatform.matchmaking.domain.exceptions.InviteCannotBeAnswered;
import com.ttplatform.matchmaking.domain.exceptions.MatchCannotStart;
import com.ttplatform.matchmaking.domain.exceptions.NotAllowed;
import com.ttplatform.matchmaking.infra.entity.PlayerInfo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Match {
    private UUID id;
    private MatchStatus matchStatus;
    private List<Game> games;
    private UUID player1;
    private String player1Name;
    private UUID player2;
    private String player2Name;
    private Instant createdAt;
    private Instant playedAt;
    private UUID winner;
    private short bestOf = 5;

    public Match(UUID player1, String player1Name, UUID player2, String player2Name) {
        this.id = UUID.randomUUID();
        this.player1 = player1;
        this.player2 = player2;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.matchStatus = MatchStatus.INVITE_PENDING;
        games = new ArrayList<>();
        winner = UUID.fromString("00000000-0000-0000-0000-000000000000");
    }

    public Match(UUID id, MatchStatus matchStatus, List<Game> games, UUID player1, String player1Name, UUID player2, String player2Name, Instant createdAt, Instant playedAt, UUID winner) {
        this.id = id;
        this.matchStatus = matchStatus;
        this.games = games;
        this.player1 = player1;
        this.player1Name = player1Name;
        this.player2 = player2;
        this.player2Name = player2Name;
        this.createdAt = createdAt;
        this.playedAt = playedAt;
        this.winner = winner;
    }


    public static Match FromInvite(User player1, User player2){
        return new Match(player1.getId(), player1.getName(), player2.getId(), player2.getName());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public UUID getPlayer1() {
        return player1;
    }

    public void setPlayer1(UUID player1) {
        this.player1 = player1;
    }

    public UUID getPlayer2() {
        return player2;
    }

    public void setPlayer2(UUID player2) {
        this.player2 = player2;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public UUID getWinner() {
        return winner;
    }

    public void setWinner(UUID winner) {
        this.winner = winner;
    }

    public Instant getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Instant playedAt) {
        this.playedAt = playedAt;
    }

    public void acceptInvite() {
        if(matchStatus != MatchStatus.INVITE_PENDING){
            throw new InviteCannotBeAnswered();
        }
        matchStatus = MatchStatus.SCHEDULED;
    }

    public void denyInvite() {
        if(matchStatus != MatchStatus.INVITE_PENDING){
            throw new InviteCannotBeAnswered();
        }

        matchStatus = MatchStatus.REJECTED;
    }

    public void starMatch(UUID playerId) {
        if(matchStatus != MatchStatus.SCHEDULED){
            throw new MatchCannotStart();
        }

        if(player1 != playerId && player2 != playerId){
            throw new NotAllowed();
        }

        matchStatus = MatchStatus.STARTED;
    }

    public void finishMatch(UUID playerId, List<Game> games){
        if(!player1.equals(playerId ) && !player2.equals(playerId)){
            throw new NotAllowed();
        }

        if(games.stream().anyMatch(game -> game.isValid() == false)){
            throw new InvalidMatchScore();
        }

        this.games = games;

        var player1wins = games.stream().filter(game -> game.getPlayer1Points() > game.getPlayer2Points()).count();
        var player2wins = games.stream().filter(game -> game.getPlayer2Points() > game.getPlayer1Points()).count();

        var setsToWin = (bestOf/2)+1;
        System.out.println("Player 1 wins: " + player1wins);
        System.out.println("Player 2 wins: " + player2wins);
        System.out.println("Sets to win: " + setsToWin);

        if(player1wins != setsToWin && player2wins != setsToWin){
            throw  new InvalidMatchScore();
        }

        this.winner = player1wins > player2wins ? player1 : player2;
        matchStatus = MatchStatus.FINISHED;
    }

}
