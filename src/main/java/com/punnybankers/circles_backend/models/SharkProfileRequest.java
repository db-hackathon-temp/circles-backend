package com.punnybankers.circles_backend.models;

import lombok.Data;

import java.util.UUID;

@Data
public class SharkProfileRequest {
    private UUID userId;
    private Long monthlyCommitment;
    private String sectorPreference;
    private String preferredCountries;
    private String payoutMode;
    private Boolean agreementSigned;
    private String participationContract;
}
