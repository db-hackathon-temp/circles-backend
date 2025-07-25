package com.punnybankers.circles_backend.repositories;

import com.punnybankers.circles_backend.repositories.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> { }
