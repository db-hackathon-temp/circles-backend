package com.punnybankers.circles_backend.util;

import com.punnybankers.circles_backend.repositories.entities.*;
import com.punnybankers.circles_backend.services.CircleService;
import com.punnybankers.circles_backend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class PayoutJob {

    @Autowired
    private CircleService circleService;
    @Autowired
    private NotificationService notificationService;
    PayoutUtil payoutUtil = new PayoutUtil();

    @Scheduled(fixedRate = 1,timeUnit = TimeUnit.DAYS)
    public void runPayout(){
        List<Circle> allCircles = circleService.getAllCircles();

        for (Circle circle : allCircles) {
            if (LocalDateTime.now().getDayOfMonth() == (circle.getPayoutDate())
                    && !circle.getStatus().equalsIgnoreCase("Active")) {
                User winner = pickWinner(circle);
                if (winner != null) {
                    circleService.payout(winner.getUsername(), circle.getId(), Long.valueOf(1000));
                    for (UserCircleEntity uc : circle.getUserCircleEntities()) {
                        User member = uc.getUser();

                        Notification notification = Notification.builder()
                                .message("Winner for this month for circle - " + circle.getName()
                                        + " is " + winner.getUsername())
                                .username(member.getUsername())
                                .isRead(false)
                                .build();

                        notificationService.saveNotification(notification);
                    }
                    if (null != circle.getShark()) {
                        Notification notification = Notification.builder()
                                .message("Winner for this month for circle - " + circle.getName() + " is " + winner.getUsername()
                                        + ". Please go to your profile page to select your contribution for winner.")
                                .username(circle.getShark().getUsername())
                                .isRead(false)
                                .build();
                        notificationService.saveNotification(notification);
                    }

                }
            }
        }
    }

    private User pickWinner(Circle circle){
        List<Payouts> payouts = circleService.getAllPayoutsForCircle(circle.getId());
        List<User> allMembers = circle.getUserCircleEntities().stream()
                .map(UserCircleEntity::getUser)
                .toList();

        if (payouts.size() < allMembers.size()){

            //User winner = payoutUtil.pickRandomFounder(circle);
            List <User> remainingFounders = new ArrayList<User>();
            List <User> payoutDone = new ArrayList<User>();
            for (Payouts payout : payouts) {
                payoutDone.add(payout.getWinner());
            }

            for (User member : allMembers) {
                if (!payoutDone.contains(member)){
                    remainingFounders.add(member);
                }
            }
            if (!remainingFounders.isEmpty()){
                User winner = payoutUtil.pickRandomFounder(remainingFounders);
                return winner;
            }


        }
        return null;
    }
}
