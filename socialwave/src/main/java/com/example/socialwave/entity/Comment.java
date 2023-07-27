package com.example.socialwave.entity;


import com.example.socialwave.statics.CommentStatus;
import com.example.socialwave.statics.CommentType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@Entity
@Table(name = "Comments")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = Post.class)
            @JoinColumn(name = "post_id")
    Post post;

    @ManyToOne(targetEntity = User.class)
            @JoinColumn(name = "user_id")
    User user;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    CommentType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    CommentStatus status;

    @Column(name = "content")
    String content;

    @Column(name = "reply_to_comment_id")
    Long replyToCommentId;

    @CreatedDate
    LocalDateTime creatDateTime;
}
