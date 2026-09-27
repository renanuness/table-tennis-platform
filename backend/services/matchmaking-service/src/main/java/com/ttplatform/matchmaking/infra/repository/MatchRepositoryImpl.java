package com.ttplatform.matchmaking.infra.repository;

import com.ttplatform.matchmaking.domain.model.Match;
import com.ttplatform.matchmaking.domain.model.MatchStatus;
import com.ttplatform.matchmaking.domain.repository.MatchRepository;
import com.ttplatform.matchmaking.infra.entity.MatchEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MatchRepositoryImpl implements MatchRepository {

    private final MatchRepositoryMongoDb matchRepositoryMongoDb;

    public MatchRepositoryImpl(MatchRepositoryMongoDb matchRepositoryMongoDb) {
        this.matchRepositoryMongoDb = matchRepositoryMongoDb;
    }

    @Override
    public Optional<Match> getById(UUID id) {
        var matchEntity = matchRepositoryMongoDb.findById(id.toString());

        return matchEntity.isPresent() ? matchEntity.map(MatchEntity::toDomain) : Optional.empty();
    }

    @Override
    public Match createMatch(Match match) {
        var saved = matchRepositoryMongoDb.save(MatchEntity.fromDomain(match));
        return saved.toDomain();
    }

    @Override
    public Match updateMatch(Match match) {
        var saved = matchRepositoryMongoDb.save(MatchEntity.fromDomain(match));
        return saved.toDomain();
    }

    @Override
    public List<Match> getUserPendingMatches(UUID userId) {
        var matches = matchRepositoryMongoDb.getUserPendingMatches(userId.toString());

        return matches.stream().map(MatchEntity::toDomain).toList();
    }
}
