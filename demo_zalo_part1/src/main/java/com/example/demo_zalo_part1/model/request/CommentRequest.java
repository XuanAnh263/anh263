package com.example.demo_zalo_part1.model.request;

import com.example.demo_zalo_part1.statics.Status;
import com.example.demo_zalo_part1.statics.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    Type type;

    @NotNull(message = "Comment status is required")
    Status status;

    @NotBlank(message = "Comment content cannot be blank")
    @Length(max = 255, message = "Comment content length must not exceed 200 characters")
    String content;

    @NotNull(message = "Reply to comment ID is required")
    Long replyToCommentId;
}
