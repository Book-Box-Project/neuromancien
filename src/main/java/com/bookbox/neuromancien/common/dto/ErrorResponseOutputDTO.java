package com.bookbox.neuromancien.common.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseOutputDTO {
    private String message;
    private LocalDateTime timestamp;
}
