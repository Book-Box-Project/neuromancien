package com.bookbox.neuromancien.auth.mapper;

import org.springframework.stereotype.Component;

import com.bookbox.neuromancien.auth.dto.UserOutputDTO;
import com.bookbox.neuromancien.auth.model.User;

@Component
public class UserMapper {

    public UserOutputDTO toOutputDTO(User user) {
        return new UserOutputDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }
}
