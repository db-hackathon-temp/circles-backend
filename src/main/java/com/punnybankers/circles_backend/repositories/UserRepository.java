package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UUID, User> {
}
