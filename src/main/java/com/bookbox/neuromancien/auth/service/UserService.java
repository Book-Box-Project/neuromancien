package com.bookbox.neuromancien.auth.service;

import com.bookbox.neuromancien.auth.dto.UserRegisterInputDTO;
import com.bookbox.neuromancien.auth.dto.UserOutputDTO;

public interface UserService {
    UserOutputDTO registerUser(UserRegisterInputDTO UserRegisterInputDTO);
}