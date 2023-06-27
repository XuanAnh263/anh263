package com.example.kanban_management.modal.request;

import com.example.kanban_management.statics.TaskStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskRequest {
    int id;

    @Size(max = 100, message = "Name cannot over 100 characters")
    @NotNull(message = "Name cannot be blank")
    String name;

    @Size(max = 255, message = "description cannot over 255 characters")
    @NotNull(message = "description cannot be blank")
    String description;
    TaskStatus status;
    LocalDate createDateTime;
    LocalDateTime expectedEndTime;

    Boolean overdue;
}
