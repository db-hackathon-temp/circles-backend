package com.punnybankers.circles_backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FounderProfileRequest {
    private String token;
    private String businessName;
    private String businessDetails;
    private String pitchUrl;
    private long fundingGoal;
    private Boolean agreementSigned;
}
