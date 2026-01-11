package com.bookbox.neuromancien.user.mapper;

import org.springframework.stereotype.Component;

import com.bookbox.neuromancien.user.dto.MeResponseDTO;
import com.bookbox.neuromancien.user.model.User;

@Component
public class UserMapper {

    public MeResponseDTO toMeResponseDTO(User user) {
        return new MeResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }
}
