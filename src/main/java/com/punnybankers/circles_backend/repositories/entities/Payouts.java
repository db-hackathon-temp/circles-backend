package com.punnybankers.circles_backend.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payouts")
public class Payouts {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payout_id", unique = true, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "circle_id")
    private Circle circle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User winner;

    @Column(name = "payout_month")
    private Month payoutMonth;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "status")
    private String status;

    @Column(name = "payout_date")
    private LocalDateTime payoutDate;
}

