package com.ttplatform.matchmaking.infra.repository;

import com.ttplatform.matchmaking.domain.model.Match;
import com.ttplatform.matchmaking.domain.repository.MatchRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MatchRepositoryImpl implements MatchRepository {

    private final MatchRepositoryMongoDb matchRepositoryMongoDb;

    public MatchRepositoryImpl(MatchRepositoryMongoDb matchRepositoryMongoDb) {
        this.matchRepositoryMongoDb = matchRepositoryMongoDb;
    }

    @Override
    public void createMatch(Match match) {

    }

    @Override
    public void updateMatch(Match match) {

    }
}
