package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.Contribution;
import com.punnybankers.circles_backend.repositories.entities.Payouts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PayoutRepository extends JpaRepository<Payouts, UUID> {
    List<Payouts> findByCircleId(UUID circleId);
}
