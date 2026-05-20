package com.example.jwtlearning.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtlearning.dto.TaskDto;
import com.example.jwtlearning.dto.TaskResponseDto;
import com.example.jwtlearning.entity.User;
import com.example.jwtlearning.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public TaskResponseDto createTask(@RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    @GetMapping("/all")
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/update/{id}")
    public TaskResponseDto updateById(@PathVariable int id, @RequestBody TaskDto taskDto) {
        return taskService.updateById(id, taskDto);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deletById(@PathVariable int id) {
        return taskService.deletById(id);
    }

}
