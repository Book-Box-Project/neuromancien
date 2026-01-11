package com.bookbox.neuromancien.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbox.neuromancien.auth.dto.UserSignupInputDTO;
import com.bookbox.neuromancien.auth.dto.UserSignupOutputDTO;
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
    public ResponseEntity<UserSignupOutputDTO> signupUser(
            @Valid @RequestBody UserSignupInputDTO userSignupInputDTO) {
        UserSignupOutputDTO userSignupOutputDTO = userService.signupUser(userSignupInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSignupOutputDTO);
    }
}
