package com.bookbox.neuromancien.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbox.neuromancien.auth.dto.UserOutputDTO;
import com.bookbox.neuromancien.auth.dto.UserRegisterInputDTO;
import com.bookbox.neuromancien.auth.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserOutputDTO> registerUser(
            @Valid @RequestBody UserRegisterInputDTO userRegisterInputDTO) {
        UserOutputDTO userOutputDTO = userService.registerUser(userRegisterInputDTO);
        return ResponseEntity.ok(userOutputDTO);
    }
}
