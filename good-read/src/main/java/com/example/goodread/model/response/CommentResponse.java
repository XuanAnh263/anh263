package com.example.goodread.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponse {

    Long id;

    Long userCommentId;

    String avatar;

    String name;

    String contentOfComment;

    LocalDate commentDate;

}
