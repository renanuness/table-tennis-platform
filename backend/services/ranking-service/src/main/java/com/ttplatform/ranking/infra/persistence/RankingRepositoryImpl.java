package com.ttplatform.ranking.infra.persistence;

import com.ttplatform.ranking.domain.model.UserRanking;
import com.ttplatform.ranking.domain.repository.RankingRepository;
import com.ttplatform.ranking.infra.persistence.entity.UserRankingEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RankingRepositoryImpl implements RankingRepository {

    private final RankingJpaRepository repository;

    public RankingRepositoryImpl(RankingJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(UserRanking userRanking) {
        var entity = UserRankingEntity.fromDomain(userRanking);

        repository.save(entity);
    }

    @Override
    public Optional<UserRanking> getUserRankingById(UUID userId) {
        var entity = repository.findById(userId);

        return entity.map(UserRankingEntity::toDomain);
    }
}
