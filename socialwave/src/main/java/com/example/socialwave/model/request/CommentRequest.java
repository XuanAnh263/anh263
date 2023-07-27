package com.example.socialwave.model.request;

import com.example.socialwave.statics.CommentStatus;
import com.example.socialwave.statics.CommentType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest {
    @NotNull(message = "Post ID is required")
    Long postId;

    @NotNull(message = "User ID is required")
    Long userId;

    @NotNull(message = "Comment type is required")
    CommentType type;

    @NotNull(message = "Comment status is required")
    CommentStatus status;

    @NotBlank(message = "Comment content cannot be blank")
    @Length(max = 255, message = "Comment content length must not exceed 200 characters")
    String content;

    @NotNull(message = "Reply to comment ID is required")
    Long replyToCommentId;
}
