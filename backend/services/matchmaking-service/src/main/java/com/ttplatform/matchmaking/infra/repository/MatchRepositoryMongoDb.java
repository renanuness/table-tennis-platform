package com.ttplatform.matchmaking.infra.repository;

import com.ttplatform.matchmaking.domain.model.MatchStatus;
import com.ttplatform.matchmaking.infra.entity.MatchEntity;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepositoryMongoDb extends MongoRepository<MatchEntity, String> {

    List<MatchEntity> findByTournamentId(String tournamentId);

    List<MatchEntity> findByPlayersUserId(String userId);

    List<MatchEntity> findByStatus(MatchStatus status);

    List<MatchEntity> findByTournamentIdAndStatus(String tournamentId, YamlProcessor.MatchStatus status);
}
