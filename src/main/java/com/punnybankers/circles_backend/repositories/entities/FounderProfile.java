package com.punnybankers.circles_backend.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "founder_profile")
public class FounderProfile {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "founder_profile_id", unique = true, nullable = false)
  private UUID founderProfileId;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "business_name", nullable = false)
  private String businessName;

  @Column(name = "business_details", nullable = false)
  private String businessDetails;

  @Column(name = "pitch_url", nullable = false)
  private String pitchUrl;

  @Column(name = "funding_goal", nullable = false)
  private Long fundingGoal;

  @Column(name = "agreement_signed", nullable = false)
  private Boolean agreementSigned;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;
}