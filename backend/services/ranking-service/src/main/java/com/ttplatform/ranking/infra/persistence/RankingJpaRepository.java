package com.ttplatform.ranking.infra.persistence;

import com.ttplatform.ranking.infra.persistence.entity.UserRankingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RankingJpaRepository extends JpaRepository<UserRankingEntity, UUID> {
}
