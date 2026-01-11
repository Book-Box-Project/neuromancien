package com.bookbox.neuromancien.common.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbox.neuromancien.common.dto.HealthCheckResponseDTO;

@RestController
public class HealthController {

    @Value("${spring.application.version:unknown}")
    private String version;

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDTO> health() {
        HealthCheckResponseDTO response = new HealthCheckResponseDTO(
                "Neuromancien is up and running",
                version,
                LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
