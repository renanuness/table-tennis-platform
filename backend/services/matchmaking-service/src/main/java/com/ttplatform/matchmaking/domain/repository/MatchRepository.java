package com.ttplatform.matchmaking.domain.repository;

import com.ttplatform.matchmaking.domain.model.Match;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatchRepository {
    Optional<Match> getById(UUID id);
    Match createMatch(Match match);
    Match updateMatch(Match match);
    List<Match> getUserPendingMatches(UUID userId);
}
