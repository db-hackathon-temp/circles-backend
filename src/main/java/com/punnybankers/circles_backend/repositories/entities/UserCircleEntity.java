package com.punnybankers.circles_backend.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "circle_membership")
@SequenceGenerator(name = "userCircleIdGenerator", sequenceName = "seq_user_circle_id", allocationSize = 1)
public class UserCircleEntity {

    @Id
    @Column(name = "user_circle_id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "circle_id", nullable = false)
    private Circle circle;
}
