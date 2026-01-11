package com.bookbox.neuromancien.common.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponseOutputDTO {
    private Map<String, String> errors;
    private LocalDateTime timestamp;
}
