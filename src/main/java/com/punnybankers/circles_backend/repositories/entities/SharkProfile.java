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
@Table(name = "shark_profile")
public class SharkProfile {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "shark_profile_id", unique = true, nullable = false)
  private UUID sharkProfileId;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "monthly_commitment", nullable = false)
  private Long monthlyCommitment;

  @Column(name = "sector_preference", nullable = false)
  private String sectorPreference;

  @Column(name = "preferred_countries", nullable = false)
  private String preferredCountries;

  @Column(name = "payout_mode", nullable = false)
  private String payoutMode;

  @Column(name = "agreement_signed", nullable = false)
  private Boolean agreementSigned;

  @Column(name = "participation_contract", nullable = false)
  private String participationContract;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;
}