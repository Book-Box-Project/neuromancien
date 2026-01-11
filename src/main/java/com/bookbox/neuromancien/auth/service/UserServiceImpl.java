package com.bookbox.neuromancien.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookbox.neuromancien.auth.dto.UserSignupInputDTO;
import com.bookbox.neuromancien.auth.dto.UserSignupOutputDTO;
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
    public UserSignupOutputDTO signupUser(UserSignupInputDTO userSignupInputDTO) {
        if (userRepository.existsByEmail(userSignupInputDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (userRepository.existsByUsername(userSignupInputDTO.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }

        User user = new User();
        user.setUsername(userSignupInputDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userSignupInputDTO.getPassword()));
        user.setEmail(userSignupInputDTO.getEmail());
        User savedUser = userRepository.save(user);
        return userMapper.toSignupOutputDTO(savedUser);
    }
}