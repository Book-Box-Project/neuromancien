package com.bookbox.neuromancien.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookbox.neuromancien.auth.dto.UserOutputDTO;
import com.bookbox.neuromancien.auth.dto.UserRegisterInputDTO;
import com.bookbox.neuromancien.auth.mapper.UserMapper;
import com.bookbox.neuromancien.auth.model.User;
import com.bookbox.neuromancien.auth.repository.UserRepository;
import com.bookbox.neuromancien.common.exception.DuplicateResourceException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserOutputDTO registerUser(UserRegisterInputDTO userRegisterInputDTO) {
        if (userRepository.existsByEmail(userRegisterInputDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (userRepository.existsByUsername(userRegisterInputDTO.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }

        User user = new User();
        user.setUsername(userRegisterInputDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterInputDTO.getPassword()));
        user.setEmail(userRegisterInputDTO.getEmail());
        User savedUser = userRepository.save(user);
        return userMapper.toOutputDTO(savedUser);
    }
}