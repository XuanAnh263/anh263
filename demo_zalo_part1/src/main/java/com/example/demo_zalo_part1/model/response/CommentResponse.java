package com.example.demo_zalo_part1.model.response;

import com.example.demo_zalo_part1.statics.Status;
import com.example.demo_zalo_part1.statics.Type;

import java.time.LocalDate;

public class CommentResponse {

    TimelineResponse timeline;
    UserResponse user;
    Type type;
    Status status;
    String content;
    LocalDate createdAt;
}
