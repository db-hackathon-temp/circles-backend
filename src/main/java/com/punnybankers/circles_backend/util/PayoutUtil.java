package com.punnybankers.circles_backend.util;

import com.punnybankers.circles_backend.repositories.entities.Circle;
import com.punnybankers.circles_backend.repositories.entities.User;

import java.util.List;
import java.util.Random;

public class PayoutUtil {

    public User pickRandomFounder(List<User> founders)
    {
        Random r = new Random();
        return founders.get(r.nextInt(founders.size()));
    }
}
