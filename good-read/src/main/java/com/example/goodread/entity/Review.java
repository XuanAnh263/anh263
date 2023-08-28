package com.example.goodread.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviewers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @Column(nullable = false)
    double rating;

    @Column(columnDefinition = "TEXT")
    String content;

    LocalDateTime deletedDateTime;
}
