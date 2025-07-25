package com.punnybankers.circles_backend.models;

import lombok.Data;

import java.util.UUID;

@Data
public class CircleRequest {
    private String name;
    private String industry;
    private String country;
    private Long monthlyContribution;
    private Integer maxMembers;
    private String createdByToken;
    private UUID sharkId;
    private Integer payoutDate;
}