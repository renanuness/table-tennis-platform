package com.ttplatform.matchmaking.infra.repository;

import com.ttplatform.matchmaking.domain.model.Match;
import com.ttplatform.matchmaking.domain.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchRepositoryImpl implements MatchRepository {

    @Autowired
    private MatchRepositoryMongoDb matchRepositoryMongoDb;
    
    @Override
    public void createMatch(Match match) {

    }

    @Override
    public void updateMatch(Match match) {

    }
}
