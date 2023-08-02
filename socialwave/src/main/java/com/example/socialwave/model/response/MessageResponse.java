package com.example.socialwave.model.response;

import com.example.socialwave.entity.Conversation;
import com.example.socialwave.entity.Sticker;
import com.example.socialwave.entity.User;
import com.example.socialwave.statics.MessageStatus;
import com.example.socialwave.statics.MessageType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResponse {
    Long id;
    Conversation conversationId;
    User senderId;
    Sticker stickerId;

    MessageType type;

    String contentText;

    String contentRickText;

    Boolean mentioned;

    Boolean deleted;

    Boolean edited;

    Long replyToMessageId;

    MessageStatus status;

    LocalDateTime createdDateTime;

    LocalDateTime lastModifiedDateTime;

}