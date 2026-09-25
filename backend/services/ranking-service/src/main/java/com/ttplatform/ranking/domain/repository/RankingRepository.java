package com.ttplatform.ranking.domain.repository;


import com.ttplatform.ranking.domain.model.UserRanking;

import java.util.Optional;
import java.util.UUID;

public interface RankingRepository {
    void save(UserRanking userRanking);

    Optional<UserRanking> getUserRankingById(UUID userId);
}
