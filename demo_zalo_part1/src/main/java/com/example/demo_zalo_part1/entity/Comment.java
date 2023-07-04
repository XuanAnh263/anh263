package com.example.demo_zalo_part1.entity;

import com.example.demo_zalo_part1.statics.Status;
import com.example.demo_zalo_part1.statics.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
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
    Type type;
    Status status;
    String content;
    Integer replyToCommentId;
    LocalDate createdAt;
}
