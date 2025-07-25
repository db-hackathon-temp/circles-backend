package com.punnybankers.circles_backend.controllers;

import com.punnybankers.circles_backend.config.JwtUtil;
import com.punnybankers.circles_backend.models.AppointmentRequest;
import com.punnybankers.circles_backend.repositories.entities.Appointment;
import com.punnybankers.circles_backend.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private final AppointmentService service;
    private final JwtUtil jwtUtil;

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

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.getAllAppointments(getUsername(token)));
    }

    public String getUsername(String token) {
                            return jwtUtil.extractUsername(token);
    }
}
