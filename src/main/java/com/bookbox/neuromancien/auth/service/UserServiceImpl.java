package com.bookbox.neuromancien.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookbox.neuromancien.auth.dto.UserSigninInputDTO;
import com.bookbox.neuromancien.auth.dto.UserSigninOutputDTO;
import com.bookbox.neuromancien.auth.dto.UserSignupInputDTO;
import com.bookbox.neuromancien.auth.dto.UserSignupOutputDTO;
import com.bookbox.neuromancien.auth.mapper.UserMapper;
import com.bookbox.neuromancien.auth.model.User;
import com.bookbox.neuromancien.auth.repository.UserRepository;
import com.bookbox.neuromancien.common.exception.DuplicateResourceException;
import com.bookbox.neuromancien.common.exception.InvalidCredentialsException;
import com.bookbox.neuromancien.common.security.JwtService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

    @Override
    public UserSigninOutputDTO signinUser(UserSigninInputDTO userSigninInputDTO) {
        User user = userRepository.findByEmail(userSigninInputDTO.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(userSigninInputDTO.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new UserSigninOutputDTO(token, user.getEmail(), user.getUsername());
    }
}