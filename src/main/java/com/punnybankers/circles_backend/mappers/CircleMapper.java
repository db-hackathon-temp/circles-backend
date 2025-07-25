package com.punnybankers.circles_backend.mappers;

import com.punnybankers.circles_backend.models.CircleResponse;
import com.punnybankers.circles_backend.repositories.entities.Circle;
import org.springframework.stereotype.Component;

@Component
public class CircleMapper {

    public static CircleResponse mapToCircleResponse(Circle circle) {
        return CircleResponse.builder()
                .id(circle.getId())
                .name(circle.getName())
                .industry(circle.getIndustry())
                .country(circle.getCountry())
                .monthlyContribution(circle.getMonthlyContribution())
                .maxMembers(circle.getMaxMembers())
                .status(circle.getStatus())
                .payoutDate(circle.getPayoutDate())
                .createdAt(circle.getCreatedAt())
                .createdByUsername(circle.getCreatedBy() != null ? circle.getCreatedBy().getUsername() : null)
                .sharkUsername(circle.getShark() != null ? circle.getShark().getUsername() : null)
                .memberCount(circle.getUserCircleEntities() != null ? circle.getUserCircleEntities().size() : 0)
                .build();
    }
}
