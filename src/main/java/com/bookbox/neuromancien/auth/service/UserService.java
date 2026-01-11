package com.bookbox.neuromancien.auth.service;

import com.bookbox.neuromancien.auth.dto.UserSigninInputDTO;
import com.bookbox.neuromancien.auth.dto.UserSigninOutputDTO;
import com.bookbox.neuromancien.auth.dto.UserSignupInputDTO;
import com.bookbox.neuromancien.auth.dto.UserSignupOutputDTO;

public interface UserService {
    UserSignupOutputDTO signupUser(UserSignupInputDTO userSignupInputDTO);

    UserSigninOutputDTO signinUser(UserSigninInputDTO userSigninInputDTO);

}