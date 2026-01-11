package com.bookbox.neuromancien.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeResponseDTO {
    private String id;
    private String username;
    private String email;
}
