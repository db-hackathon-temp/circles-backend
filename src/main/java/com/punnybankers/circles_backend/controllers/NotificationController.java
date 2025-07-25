package com.punnybankers.circles_backend.controllers;

import com.punnybankers.circles_backend.repositories.entities.Notification;
import com.punnybankers.circles_backend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications(@RequestHeader("token") String token) {
        List<Notification> notifications = notificationService.findByUsername(token);
        return ResponseEntity.ok(notifications);
    }


    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable String id) {
        notificationService.markNotificationAsRead(id);
        return ResponseEntity.ok().build();

    }
}
