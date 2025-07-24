package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.Circle;
import com.punnybankers.circles_backend.repositories.entities.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, UUID> {
}
