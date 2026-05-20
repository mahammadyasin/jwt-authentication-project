package com.example.jwtlearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LOginResponseDto {
    private String jwtToken;
    private int user_id;
}
