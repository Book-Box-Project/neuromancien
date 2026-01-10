package com.bookbox.neuromancien.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bookbox.neuromancien.common.dto.ErrorResponseOutputDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseOutputDTO> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponseOutputDTO error = new ErrorResponseOutputDTO(
                ex.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseOutputDTO> handleGenericException(Exception ex) {
        ErrorResponseOutputDTO error = new ErrorResponseOutputDTO(
                "An error occurred: " + ex.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);

    }

}
