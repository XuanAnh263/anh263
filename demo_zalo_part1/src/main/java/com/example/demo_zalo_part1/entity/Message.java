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
    Type type;
    String contentText;
    String contentRickText;
    Boolean mentioned;
    Boolean deleted;
    Boolean edited;
    Integer replyToMessageId;
    Status status;
    LocalDate createdAt;
    LocalDate updatedAt;
}
