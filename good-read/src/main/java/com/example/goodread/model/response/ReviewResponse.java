package com.example.goodread.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {
    Long currentUserId;

    Long reviewId;

    Long userReviewId;

    String avatar;

    String name;

    LocalDate reviewDate;

    double ratingDetail;

    String content;

    Integer likes;

    Boolean following;

    Boolean liked;

    List<CommentResponse> childComments;

    Integer comments;

}
