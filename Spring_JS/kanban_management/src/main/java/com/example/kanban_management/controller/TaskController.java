package com.example.kanban_management.controller;

import com.example.kanban_management.modal.request.TaskRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {


    @GetMapping("/tasks")
    public String getTask(Module module) {

        return "index";
    }


    @GetMapping("/api/v1/tasks{id}")
    public ResponseEntity<?> taskDetail(@PathVariable Integer id) {

    }

    @PostMapping("/api/v1/tasks")
    public ResponseEntity<?> createNewTask(@RequestBody @Valid TaskRequest taskRequest) {

    }

    @PutMapping("/api/v1/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Integer id) {

    }

    @DeleteMapping("/api/v1/task/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

    }
}
