package com.example.demo_zalo_part1.model.request;

import com.example.demo_zalo_part1.statics.Status;
import com.example.demo_zalo_part1.statics.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Builder
@ToString

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest {

    PostRequest post;
    UserRequest user;
    Type type;
    Status status;
    String content;
    Integer replyToCommentId;
    LocalDate createdAt;
}
