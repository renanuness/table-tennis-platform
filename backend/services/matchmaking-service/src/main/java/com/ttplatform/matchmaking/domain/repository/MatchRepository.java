package com.ttplatform.matchmaking.domain.repository;

import com.ttplatform.matchmaking.domain.model.Match;

public interface MatchRepository {
    void createMatch(Match match);
    void updateMatch(Match match);
}
