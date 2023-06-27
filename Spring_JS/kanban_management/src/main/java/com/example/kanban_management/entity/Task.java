package com.example.kanban_management.entity;

import com.example.kanban_management.statics.TaskStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    int id;
    String name;
    String description;
    TaskStatus status;
    LocalDate createDateTime;
    LocalDateTime expectedEndTime;

    Boolean overdue;
}
