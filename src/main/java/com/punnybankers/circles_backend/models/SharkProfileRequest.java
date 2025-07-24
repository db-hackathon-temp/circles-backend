package com.punnybankers.circles_backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharkProfileRequest {
    private String token;
    private Long monthlyCommitment;
    private String sectorPreference;
    private String preferredCountries;
    private String payoutMode;
    private Boolean agreementSigned;
    private String participationContract;
}
