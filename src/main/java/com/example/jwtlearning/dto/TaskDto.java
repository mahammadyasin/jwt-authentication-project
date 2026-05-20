package com.example.jwtlearning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDto {
    private String title;
    private String description;
}
