package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;
import java.util.UUID;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, UUID> {
    List<Contribution> findAllByCircleId(UUID circleId);
    Contribution findByCircleIdAndUserNameAndContributionMonth(UUID circleId, String userName, Month month);
}
