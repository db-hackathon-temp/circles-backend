package com.punnybankers.circles_backend.services;

import com.punnybankers.circles_backend.controllers.UserController;
import com.punnybankers.circles_backend.repositories.NotificationRepository;
import com.punnybankers.circles_backend.repositories.entities.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserController userController;

    public List<Notification> findByUsername(String token) {
        String username = userController.getUsername(token);
        return notificationRepository.findByUsername(username);
    }

    public void markNotificationAsRead(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Notification> notification = notificationRepository.findById(uuid);
        if (notification.isPresent()) {
            notification.get().setRead(true);
            notificationRepository.save(notification.get());
        } else {
            throw new RuntimeException("Notification not found");
        }
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
