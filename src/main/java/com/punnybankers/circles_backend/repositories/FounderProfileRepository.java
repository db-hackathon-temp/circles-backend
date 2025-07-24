package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.FounderProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FounderProfileRepository extends JpaRepository<FounderProfile, UUID> { }
