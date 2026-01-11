package com.bookbox.neuromancien.auth.service;

import com.bookbox.neuromancien.auth.dto.SigninRequestDTO;
import com.bookbox.neuromancien.auth.dto.SigninResponseDTO;
import com.bookbox.neuromancien.auth.dto.SignupRequestDTO;
import com.bookbox.neuromancien.auth.dto.SignupResponseDTO;

public interface AuthService {
    SignupResponseDTO signup(SignupRequestDTO userSignupInputDTO);

    SigninResponseDTO signin(SigninRequestDTO userSigninInputDTO);

}