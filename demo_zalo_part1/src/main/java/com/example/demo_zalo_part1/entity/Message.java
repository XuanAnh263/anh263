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
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = Conversation.class)
            @JoinColumn(name = "conversation_id")
    Conversation conversation;

    @ManyToOne(targetEntity = User.class)
            @JoinColumn(name = "sender_id")
    User senderId;

    @ManyToOne(targetEntity = Sticker.class)
            @JoinColumn(name = "sticker_id")
    Sticker sticker;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    Type type;

    @Column(name = "content_text")
    String contentText;

    @Column(name = "content_rick_text")
    String contentRickText;

    @Column(name = "mentioned")
    Boolean mentioned;

    @Column(name = "deleted")
    Boolean deleted;

    @Column(name = "edited")
    Boolean edited;

    @Column(name = "reply_to_message_id")
    Long replyToMessageId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @CreatedDate
    LocalDateTime creatDateTime;

    @LastModifiedDate
    LocalDateTime lastModifiedDateTime;
}
