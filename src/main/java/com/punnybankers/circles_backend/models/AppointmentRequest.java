package com.punnybankers.circles_backend.models;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AppointmentRequest {
    private UUID sharkUserId;
    private UUID founderUserId;
    private Instant scheduledTime;
    private String status;

    // Getters and setters (or use Lombok @Data if you want)
}
