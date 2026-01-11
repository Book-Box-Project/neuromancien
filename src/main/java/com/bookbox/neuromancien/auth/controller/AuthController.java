package com.bookbox.neuromancien.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbox.neuromancien.auth.dto.SigninRequestDTO;
import com.bookbox.neuromancien.auth.dto.SigninResponseDTO;
import com.bookbox.neuromancien.auth.dto.SignupRequestDTO;
import com.bookbox.neuromancien.auth.dto.SignupResponseDTO;
import com.bookbox.neuromancien.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(
            @Valid @RequestBody SignupRequestDTO signupRequestDTO) {
        SignupResponseDTO signupResponseDTO = authService.signup(signupRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(signupResponseDTO);
    }

    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDTO> signin(
            @Valid @RequestBody SigninRequestDTO signinRequestDTO) {
        SigninResponseDTO signinResponseDTO = authService.signin(signinRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(signinResponseDTO);
    }
}
