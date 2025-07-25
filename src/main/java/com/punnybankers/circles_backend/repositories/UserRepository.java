package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.User;
import com.punnybankers.circles_backend.repositories.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    List<User> findAllByRole(UserRole role);
}

