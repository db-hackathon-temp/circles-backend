package com.punnybankers.circles_backend.services;


import com.punnybankers.circles_backend.repositories.CircleRepository;
import com.punnybankers.circles_backend.repositories.ContributionRepository;
import com.punnybankers.circles_backend.repositories.entities.Circle;
import com.punnybankers.circles_backend.repositories.entities.Contribution;
import com.punnybankers.circles_backend.repositories.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CircleService {
    @Autowired
    private CircleRepository circleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ContributionRepository contributionRepository;



    public Optional<Circle> findById(UUID circleId) {
        return circleRepository.findById(circleId);
    }

    public Circle save(Circle circle) {
        return circleRepository.save(circle);
    }

    public boolean addSharkToCircle(UUID circleId, UUID userId) {
        Optional<Circle> circleOpt = circleRepository.findById(circleId);
        if (circleOpt.isEmpty()) return false;

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) return false;

        Circle circle = circleOpt.get();
        User sharkUser = userOpt.get();

        circle.setShark(sharkUser);
        circleRepository.save(circle);
        return true;
    }


    public boolean addMemberToCircle(UUID circleId, UUID userId) {
        Optional<Circle> circleOpt = circleRepository.findById(circleId);
        if (circleOpt.isEmpty()) return false;

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) return false;

        Circle circle = circleOpt.get();
        User memberUser = userOpt.get();

        circle.getMembers().add(memberUser);
        memberUser.getCircles().add(circle);

        circleRepository.save(circle);
        userService.save(memberUser);

        return true;
    }

    public void contribute(String userName, UUID circleId) {
        Optional<User> user = Optional.ofNullable(userService.findByUsername(userName));
        if (user.isPresent()) {
            Optional<Circle> circle = findById(circleId);
            if (circle.isPresent()) {
                Contribution contribution = Contribution.builder()
                        .circle(circle.get())
                        .contributionMonth(LocalDateTime.now().getMonth())
                        .amount(circle.get().getMonthlyContribution())
                        .payoutDate(LocalDateTime.now())
                        .status("Paid")
                        .build();
                contributionRepository.save(contribution);
            }
        }

    }


}
