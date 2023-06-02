package com.example.taskmanagement.model.response;

import com.example.taskmanagement.statics.TaskStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {
    TaskStatus status;
    List<TaskDetailResponse> taskDetails;

}
