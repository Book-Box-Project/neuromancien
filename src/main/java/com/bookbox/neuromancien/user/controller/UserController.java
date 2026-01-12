package com.bookbox.neuromancien.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbox.neuromancien.user.dto.MeResponseDTO;
import com.bookbox.neuromancien.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<MeResponseDTO> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        MeResponseDTO response = userService.getCurrentUser(email);
        return ResponseEntity.ok(response);
    }
}