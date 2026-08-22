package com.ttplatform.matchmaking.infra.repository;

import com.ttplatform.matchmaking.domain.model.MatchStatus;
import com.ttplatform.matchmaking.infra.entity.Match;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepositoryMongoDb extends MongoRepository<Match, String> {

    List<Match> findByTournamentId(String tournamentId);

    List<Match> findByPlayersUserId(String userId);

    List<Match> findByStatus(MatchStatus status);

    List<Match> findByTournamentIdAndStatus(String tournamentId, YamlProcessor.MatchStatus status);
}
