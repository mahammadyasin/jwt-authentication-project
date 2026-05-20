package com.example.jwtlearning.mapper;

import com.example.jwtlearning.dto.TaskDto;
import com.example.jwtlearning.dto.TaskResponseDto;
import com.example.jwtlearning.entity.Task;
import com.example.jwtlearning.entity.User;

public class TaskMapper {
    public static Task toTaskEntity(TaskDto taskDto, User user) {
        return Task.builder().title(taskDto.getTitle()).description(taskDto.getDescription())
                .status("pending").user(user).build();

    }

    public static TaskResponseDto toTaskDto(Task task) {
        return TaskResponseDto.builder().id(task.getId()).title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus()).username(task.getUser().getUsername()).build();

    }
}
