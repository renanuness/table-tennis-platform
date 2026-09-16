package com.ttplatform.matchmaking.infra.entity;

import com.ttplatform.matchmaking.domain.model.Game;
import com.ttplatform.matchmaking.domain.model.Match;
import com.ttplatform.matchmaking.domain.model.MatchStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "matches")
public class MatchEntity {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private MatchStatus status; // SCHEDULED, IN_PROGRESS, FINISHED, CANCELLED
    private List<PlayerInfo> players;
    // Sets com games aninhados
    private List<GameInfo> games;
    private String matchWinner;

    private Instant playedAt;
    private Instant createdAt;

    public MatchEntity(String id , MatchStatus status, List<PlayerInfo> players, List<GameInfo> games, String matchWinner, Instant playedAt, Instant createdAt) {
        this.id = id;
        this.status = status;
        this.players = players;
        this.games = games;
        this.matchWinner = matchWinner;
        this.playedAt = playedAt;
        this.createdAt = createdAt;
    }

    public static MatchEntity fromDomain(Match match){
        var players = new ArrayList<PlayerInfo>();
        players.add(new PlayerInfo(match.getPlayer1().toString(),match.getPlayer1Name()));
        players.add(new PlayerInfo(match.getPlayer2().toString(),match.getPlayer2Name()));
        var games = gamesFromDomain(match);

        return new MatchEntity(
                match.getId().toString(),
                match.getMatchStatus(),
                players,
                games,
                match.getWinner().toString(),
                match.getCreatedAt(),
                match.getPlayedAt()
        );
    }

    public Match toDomain(){
        var games = gamesToDomain();
        var match = new Match(
                UUID.fromString(this.id),
                status,
                games,
                UUID.fromString(players.get(0).userId()),
                players.get(0).name(),
                UUID.fromString(players.get(1).userId()),
                players.get(1).userId(),
                createdAt,
                playedAt,
                UUID.fromString(matchWinner)
        );
        return match;
    }

    private static List<GameInfo> gamesFromDomain(Match match){

        var gamesInfo = new ArrayList<GameInfo>();
        for(var i = 1; i <= match.getGames().size(); i++){
            var game = match.getGames().get(i);
            var gameInfo = new GameInfo(
                    1,
                    match.getPlayer1().toString(),
                    match.getPlayer2().toString(),
                    game.getPlayer1Points(),
                    game.getPlayer2Points()
            );

            gamesInfo.add(gameInfo);
        }
        return gamesInfo;
    }

    private List<Game> gamesToDomain(){
        var games = new ArrayList<Game>();
        for(var i = 1; i <= this.games.size(); i++){
            var gameInfo = this.games.get(i);
            var game = new Game(
                    gameInfo.getPlayerPoints(players.get(0).userId()),
                    gameInfo.getPlayerPoints(players.get(1).userId())
            );

            games.add(game);
        }
        return games;
    }
}

