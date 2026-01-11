package com.bookbox.neuromancien.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookbox.neuromancien.auth.dto.SigninRequestDTO;
import com.bookbox.neuromancien.auth.dto.SigninResponseDTO;
import com.bookbox.neuromancien.auth.dto.SignupRequestDTO;
import com.bookbox.neuromancien.auth.dto.SignupResponseDTO;
import com.bookbox.neuromancien.auth.mapper.AuthMapper;
import com.bookbox.neuromancien.common.exception.DuplicateResourceException;
import com.bookbox.neuromancien.common.exception.InvalidCredentialsException;
import com.bookbox.neuromancien.common.security.JwtService;
import com.bookbox.neuromancien.user.model.User;
import com.bookbox.neuromancien.user.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, AuthMapper authMapper, PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public SignupResponseDTO signup(SignupRequestDTO userSignupInputDTO) {
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
        return authMapper.toSignupResponseDTO(savedUser);
    }

    @Override
    public SigninResponseDTO signin(SigninRequestDTO userSigninInputDTO) {
        User user = userRepository.findByEmail(userSigninInputDTO.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException());

        if (!passwordEncoder.matches(userSigninInputDTO.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user.getEmail());

        return authMapper.toSigninResponseDTO(token, user);
    }
}