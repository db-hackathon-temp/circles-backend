package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.SharkProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SharkProfileRepository extends JpaRepository<SharkProfile, UUID> { }
