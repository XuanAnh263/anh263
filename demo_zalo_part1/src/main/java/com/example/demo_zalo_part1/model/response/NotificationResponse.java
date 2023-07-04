package com.example.demo_zalo_part1.model.response;

import com.example.demo_zalo_part1.statics.Status;

import java.time.LocalDate;

public class NotificationResponse {

    UserResponse user;
    String type;
    String content;
    Status status;
    Boolean read;
    LocalDate createdAt;
    LocalDate updatedAt;
}
