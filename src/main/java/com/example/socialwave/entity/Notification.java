package com.example.socialwave.entity;

import com.example.socialwave.statics.NotificationStatus;
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
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = User.class)
            @JoinColumn(name = "user_id")
    User user;

    @Column(name = "type")
    String type;

    @Column(name = "content")
    String content;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    NotificationStatus status;

    @Column(name = "[read]", columnDefinition = "BOOLEAN")
    Boolean read;

    @CreatedDate
    LocalDateTime creatDateTime;

    @LastModifiedDate
    LocalDateTime lastModifiedDateTime;
}
