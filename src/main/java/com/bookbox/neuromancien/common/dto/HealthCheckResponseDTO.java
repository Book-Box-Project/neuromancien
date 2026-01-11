package com.bookbox.neuromancien.common.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthCheckResponseDTO {
    private String service;
    private String version;
    private LocalDateTime timestamp;
}
