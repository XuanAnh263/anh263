package com.example.demo_zalo_part1.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Builder
@ToString
@Entity
@Table(name = "media_files")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = Post.class)
            @JoinColumn(name = "post_id")
    Post post;

    @ManyToOne(targetEntity = File.class)
            @JoinColumn(name = "file_id")
    File file;

    @ManyToOne(targetEntity = User.class)
            @JoinColumn(name = "creator_id")
    User creatorId;
}
