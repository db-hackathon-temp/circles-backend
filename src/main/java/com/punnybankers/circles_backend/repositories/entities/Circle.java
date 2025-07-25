package com.punnybankers.circles_backend.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "circles")
public class Circle {

    @Id
    @Column(name = "circle_id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "industry")
    private String industry;

    @Column(name = "country")
    private String country;

    @Column(name = "monthly_contribution")
    private Long monthlyContribution;

    @Column(name = "max_members")
    private Integer maxMembers;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "payout_date")
    private Integer payoutDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shark_id")
    private User shark;

    @ManyToMany(mappedBy = "circles")
    private List<User> members = new ArrayList<>();
}