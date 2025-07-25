package com.punnybankers.circles_backend.services;

import com.punnybankers.circles_backend.models.AppointmentRequest;
import com.punnybankers.circles_backend.repositories.entities.Appointment;
import com.punnybankers.circles_backend.repositories.entities.User;
import com.punnybankers.circles_backend.repositories.AppointmentRepository;
import com.punnybankers.circles_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepo;
    private final UserRepository userRepo;

    public AppointmentService(AppointmentRepository appointmentRepo, UserRepository userRepo) {
        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
    }

    public Appointment createAppointment(AppointmentRequest req) {
        User shark = userRepo.findById(req.getSharkUserId())
            .orElseThrow(() -> new IllegalArgumentException("Shark user not found"));
        User founder = userRepo.findById(req.getFounderUserId())
            .orElseThrow(() -> new IllegalArgumentException("Founder user not found"));

        Appointment appointment = Appointment.builder()
            .sharkUser(shark)
            .founderUser(founder)
            .scheduledTime(req.getScheduledTime())
            .status(req.getStatus())
            .createdAt(Instant.now())
            .build();

        return appointmentRepo.save(appointment);
    }

    public Optional<Appointment> getAppointment(UUID id) {
        return appointmentRepo.findById(id);
    }
}
