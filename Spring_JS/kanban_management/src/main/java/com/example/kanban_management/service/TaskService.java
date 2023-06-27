package com.example.kanban_management.service;

import com.example.kanban_management.entity.Task;
import com.example.kanban_management.modal.response.TaskDetailResponse;
import com.example.kanban_management.modal.response.TaskResponse;
import com.example.kanban_management.repository.TaskRepository;
import com.example.kanban_management.statics.TaskStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService {
    TaskRepository taskRepository;
    ObjectMapper objectMapper;

    public List<TaskResponse> getAll() {
        List<Task> tasks = taskRepository.getAll();
        List<TaskDetailResponse> tempData = tasks
                .stream()
                .map(task -> objectMapper.convertValue(task, TaskDetailResponse.class)).toList();

        List<TaskStatus> taskStatuses = Arrays.asList(TaskStatus.values());

        List<TaskResponse> result = new ArrayList<>();
        for (int i = 0; i < taskStatuses.size(); i++) {
            TaskStatus taskStatus = taskStatuses.get(i);
            List<TaskDetailResponse> list = new ArrayList<>();

            for (int j = 0; j < tempData.size(); j++) {
                if (tempData.get(j).getStatus().equals(taskStatus)) {
                    list.add(tempData.get(j));
                }
            }
            TaskResponse taskResponse = new TaskResponse(taskStatus, list);
            result.add(taskResponse);
        }
        return result;
    }
}
