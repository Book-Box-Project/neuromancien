package com.bookbox.neuromancien.auth.mapper;

import org.springframework.stereotype.Component;

import com.bookbox.neuromancien.auth.dto.SigninResponseDTO;
import com.bookbox.neuromancien.auth.dto.SignupResponseDTO;
import com.bookbox.neuromancien.user.model.User;

@Component
public class AuthMapper {

    public SignupResponseDTO toSignupResponseDTO(User user) {
        return new SignupResponseDTO(
                "User signup successfully",
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }

    public SigninResponseDTO toSigninResponseDTO(String token, User user) {
        return new SigninResponseDTO(
                token,
                user.getEmail(),
                user.getUsername());
    }
}
