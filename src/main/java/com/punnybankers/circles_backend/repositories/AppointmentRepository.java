package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.Appointment;
import com.punnybankers.circles_backend.repositories.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findAllByFounderUser(User founderUser);
    List<Appointment> findAllBySharkUser(User user);
}
