package com.punnybankers.circles_backend.services;


import com.punnybankers.circles_backend.controllers.UserController;
import com.punnybankers.circles_backend.mappers.CircleMapper;
import com.punnybankers.circles_backend.models.CircleRequest;
import com.punnybankers.circles_backend.models.CircleResponse;
import com.punnybankers.circles_backend.repositories.*;
import com.punnybankers.circles_backend.repositories.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private UserCircleRepository userCircleRepository;

    @Autowired
    private PayoutRepository payoutRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private NotificationService notificationService;

    public List<CircleResponse> getAllCirclesByToken(String token) {
        String username = userController.getUsername(token);
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getUserCircleEntities()
                .stream()
                .map(UserCircleEntity::getCircle)
                .map(CircleMapper::mapToCircleResponse)
                .toList();
    }

    public CircleResponse createCircle(CircleRequest request) {
        String username = userController.getUsername(request.getCreatedByToken());
        User createdByUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        User sharkUser = null;
        if (request.getSharkId() != null) {
            sharkUser = userService.findById(request.getSharkId())
                    .orElseThrow(() -> new RuntimeException("Shark user not found"));
        }

        Circle circle = Circle.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .industry(request.getIndustry())
                .country(request.getCountry())
                .monthlyContribution(request.getMonthlyContribution())
                .maxMembers(request.getMaxMembers())
                .createdBy(createdByUser)
                .payoutDate(request.getPayoutDate())
                .shark(sharkUser)
                .status("ACTIVE")
                .createdAt(LocalDateTime.now())
                .build();
        Notification notification = Notification.builder()
                .message("Circle created successfully!")
                .username(username)
                .isRead(false)
                .build();
        UserCircleEntity membership = UserCircleEntity.builder()
                .user(createdByUser)
                .circle(circle)
                .build();
        notificationService.saveNotification(notification);
        Circle created_circle = circleRepository.save(circle);
        userCircleRepository.save(membership);
        return CircleMapper.mapToCircleResponse(circle);
    }

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

        Notification notification = Notification.builder()
                .message("You are now added to the circle - " + circle.getName())
                .username(sharkUser.getUsername())
                .isRead(false)
                .build();
        notificationService.saveNotification(notification);
        return true;
    }


    public boolean addMemberToCircle(UUID circleId, UUID userId) {
        Optional<Circle> circleOpt = circleRepository.findById(circleId);
        if (circleOpt.isEmpty()) return false;

        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isEmpty()) return false;

        Circle circle = circleOpt.get();
        User memberUser = userOpt.get();

        UserCircleEntity userCircle = UserCircleEntity.builder()
                .user(memberUser)
                .circle(circle)
                .build();

        userCircleRepository.save(userCircle);

        circle.getUserCircleEntities().add(userCircle);
        memberUser.getUserCircleEntities().add(userCircle);

        circleRepository.save(circle);
        userService.save(memberUser);

        Notification notification = Notification.builder()
                .message("You are now added to the circle - " + circle.getName())
                .username(memberUser.getUsername())
                .isRead(false)
                .build();
        notificationService.saveNotification(notification);
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
                        .paymentDate(LocalDateTime.now())
                        .status("Paid")
                        .build();
                contributionRepository.save(contribution);
            }
        }

    }

    public List<Circle> getAllCircles() {
        return new ArrayList<>(circleRepository.findAll());
    }

    public void payout(String userName, UUID circleId, Long amount) {
        Optional<User> winner = Optional.ofNullable(userService.findByUsername(userName));
        if (winner.isPresent()) {
            Optional<Circle> circle = findById(circleId);
            if (circle.isPresent()) {
                Payouts payout = Payouts.builder()
                        .circle(circle.get())
                        .payoutMonth(LocalDateTime.now().getMonth())
                        .amount(amount)
                        .payoutDate(LocalDateTime.now())
                        .status("Paid")
                        .winner(winner.get())
                        .build();
                payoutRepository.save(payout);
            }
        }
    }

    public List<Payouts> getAllPayoutsForCircle(UUID circleId) {
        return new ArrayList<>(payoutRepository.findByCircleId(circleId));
    }

    public List<Contribution> getAllContributionsForCircle(UUID circleId) {
        return new ArrayList<>(contributionRepository.findAllByCircleId(circleId));
    }

//    public Contribution getContributionForCircleUserAndMonth(UUID circleId, String userName,  Month month) {
//        return contributionRepository.findByCircleIdAndUserNameAndContributionMonth(circleId, userName, month);
//    }

}
