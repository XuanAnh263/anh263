package com.example.demo_zalo_part1.entity;

import com.example.demo_zalo_part1.statics.Status;
import com.example.demo_zalo_part1.statics.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@Entity
@Table(name = "Conversations")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    Type type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @CreatedDate
    LocalDateTime creatDateTime;

    @LastModifiedDate
    LocalDateTime lastModifiedDateTime;
}
