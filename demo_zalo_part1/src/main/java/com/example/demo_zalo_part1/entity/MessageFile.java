package com.example.demo_zalo_part1.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@ToString
@Entity
@Table(name = "message_files")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = Message.class)
            @JoinColumn(name = "message_id")
    Message message;

    @ManyToOne(targetEntity = File.class)
            @JoinColumn(name = "file_id")
    File file;

    @ManyToOne(targetEntity = User.class)
            @JoinColumn(name = "creator_id")
    User creatorId;
}
