package com.bookbox.neuromancien.user.service;

import com.bookbox.neuromancien.user.dto.MeResponseDTO;

public interface UserService {
    MeResponseDTO getCurrentUser(String email);
}