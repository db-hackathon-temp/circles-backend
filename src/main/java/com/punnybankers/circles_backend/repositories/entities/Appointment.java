package com.punnybankers.circles_backend.repositories.entities;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "shark_user_id", nullable = false)
    private User sharkUser;

    @ManyToOne
    @JoinColumn(name = "founder_user_id", nullable = false)
    private User founderUser;

    private Instant scheduledTime;
    private String status;
    private Instant createdAt;
}
