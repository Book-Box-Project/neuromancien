package com.bookbox.neuromancien.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupOutputDTO {
    private String message;
    private String id;
    private String username;
    private String email;
}
