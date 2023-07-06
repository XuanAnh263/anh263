package com.example.demo_zalo_part1.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@ToString

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeRequest {


    PostRequest post;
    UserRequest user;
    CommentRequest comment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime creatDateTime;
}
