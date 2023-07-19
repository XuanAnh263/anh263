package com.example.socialwave.entity;


import com.example.socialwave.statics.ConversationStatus;
import com.example.socialwave.statics.ConversationType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
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
    ConversationType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    ConversationStatus status;

    @CreatedDate
    LocalDateTime creatDateTime;

    @LastModifiedDate
    LocalDateTime lastModifiedDateTime;
}
