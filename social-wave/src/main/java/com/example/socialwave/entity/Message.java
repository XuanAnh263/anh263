package com.example.socialwave.entity;


import com.example.socialwave.statics.MessageStatus;
import com.example.socialwave.statics.MessageType;
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
    MessageType type;

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
    MessageStatus status;

    @CreatedDate
    LocalDateTime creatDateTime;

    @LastModifiedDate
    LocalDateTime lastModifiedDateTime;
}
