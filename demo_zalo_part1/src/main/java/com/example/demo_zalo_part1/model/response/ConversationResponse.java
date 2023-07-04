package com.example.demo_zalo_part1.model.response;

import com.example.demo_zalo_part1.statics.Status;

import java.time.LocalDate;

public class ConversationResponse {

    String name;
    String description;
    String type;
    Status status;
    LocalDate createdAt;
    LocalDate updatedAt;
}
