package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.UserCircleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCircleRepository extends JpaRepository<UserCircleEntity, Integer> {
}
