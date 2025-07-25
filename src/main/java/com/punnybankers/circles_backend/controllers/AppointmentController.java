package com.punnybankers.circles_backend.controllers;

import com.punnybankers.circles_backend.models.AppointmentRequest;
import com.punnybankers.circles_backend.repositories.entities.Appointment;
import com.punnybankers.circles_backend.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Appointment> create(@RequestBody AppointmentRequest req) {
        return ResponseEntity.ok(service.createAppointment(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> get(@PathVariable UUID id) {
        return service.getAppointment(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
