package com.example.kanban_management.statics;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public enum TaskStatus {
    TODO("To do"),
    IN_PROGRESS("In Progress"),
    REVIEWING("Reviewing"),
    DONE("Done");
    String name;
}
