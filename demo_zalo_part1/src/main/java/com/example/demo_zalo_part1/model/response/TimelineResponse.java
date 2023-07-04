package com.example.demo_zalo_part1.model.response;

import java.time.LocalDate;

public class TimelineResponse {

    UserResponse user;
    String contentText;
    String contentMedia;
    Integer likeCount;
    Integer commentCount;
    LocalDate createdAt;
    LocalDate updateAt;
}
