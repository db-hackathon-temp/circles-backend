package com.punnybankers.circles_backend.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "notification_id", nullable = false)
    private UUID notificationId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;
}
