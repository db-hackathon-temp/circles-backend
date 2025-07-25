package com.punnybankers.circles_backend.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
public class CircleResponse{
    private UUID id;
    private String name;
    private String industry;
    private String country;
    private Long monthlyContribution;
    private Integer maxMembers;
    private String status;
    private Integer payoutDate;
    private LocalDateTime createdAt;

    // Creator and Shark info (optional)
    private String createdByUsername;
    private String sharkUsername;

    // Optional: add number of members if needed
    private Integer memberCount;
}
