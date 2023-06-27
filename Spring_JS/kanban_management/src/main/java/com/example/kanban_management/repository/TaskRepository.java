package com.example.kanban_management.repository;

import com.example.kanban_management.entity.Task;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskRepository {
    static int AUTO_ID = 1;

    final List<Task> tasks = new ArrayList<>();

    public List<Task> getAll() {
        return tasks;
    }


}
