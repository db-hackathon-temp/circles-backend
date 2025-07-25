package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByUsername(String username);
}
