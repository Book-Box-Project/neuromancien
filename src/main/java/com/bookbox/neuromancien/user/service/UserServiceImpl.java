package com.bookbox.neuromancien.user.service;

import org.springframework.stereotype.Service;

import com.bookbox.neuromancien.common.exception.ResourceNotFoundException;
import com.bookbox.neuromancien.user.dto.MeResponseDTO;
import com.bookbox.neuromancien.user.mapper.UserMapper;
import com.bookbox.neuromancien.user.model.User;
import com.bookbox.neuromancien.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public MeResponseDTO getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toMeResponseDTO(user);
    }

}
