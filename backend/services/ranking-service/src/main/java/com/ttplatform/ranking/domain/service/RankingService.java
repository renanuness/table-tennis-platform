package com.ttplatform.ranking.domain.service;

import com.ttplatform.ranking.domain.exception.UserNotFoundException;
import com.ttplatform.ranking.domain.model.MatchResult;
import com.ttplatform.ranking.domain.model.UserRanking;
import com.ttplatform.ranking.domain.repository.RankingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RankingService {
    private final RankingRepository rankingRepository;

    public RankingService(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    public void createUserRanking(UUID userId){
        rankingRepository.save(UserRanking.newUser(userId));
    }

    @Transactional
    public void updateUsersRanking(UUID winnerId, UUID loserId){
        updateUserRanking(winnerId, MatchResult.WIN);
        updateUserRanking(loserId, MatchResult.LOSS);
    }

    private void updateUserRanking(UUID userId, MatchResult result){
        var userRanking = rankingRepository.getUserRankingById(userId)
                .orElseThrow(UserNotFoundException::new);

        userRanking.updatePoints(result);
    }
}
