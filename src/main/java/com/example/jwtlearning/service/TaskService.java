package com.example.jwtlearning.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.jwtlearning.dto.TaskDto;
import com.example.jwtlearning.dto.TaskResponseDto;
import com.example.jwtlearning.entity.Task;
import com.example.jwtlearning.entity.User;
import com.example.jwtlearning.mapper.TaskMapper;
import com.example.jwtlearning.repository.TaskRepository;
import com.example.jwtlearning.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskResponseDto createTask(TaskDto taskDto) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Task savedtask = TaskMapper.toTaskEntity(taskDto, user);
        taskRepository.save(savedtask);
        TaskResponseDto taskResponseDto = TaskMapper.toTaskDto(savedtask);
        return taskResponseDto;
    }

    public List<TaskResponseDto> getAllTasks() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return taskRepository.findByUser(user).stream()
                .map(TaskMapper::toTaskDto)
                .toList();
    }

    // public List<TaskResponseDto> getAllTasks() {
    // return taskRepository.findAll().stream().map(TaskMapper::toTaskDto).toList();

    // }

    public TaskResponseDto updateById(int id, TaskDto taskDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        Task task = taskRepository.findById(id).orElseThrow();
        try {
            if (!task.getUser().getUsername().equals(user.getUsername())) {
                throw new RuntimeException("user name not currect enter currect name");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // if (!task.getUser().getUsername().equals(user.getUsername())) {
        // throw new RuntimeException("user not found please login");

        // }
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus("pending");

        Task saved = taskRepository.save(task);

        return TaskMapper.toTaskDto(saved);

    }

    public String deletById(int id) {
        taskRepository.deleteById(id);
        return id + "task is  deleted  ";
    }

}
