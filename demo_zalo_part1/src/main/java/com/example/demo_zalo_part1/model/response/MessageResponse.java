package com.example.demo_zalo_part1.model.response;

import com.example.demo_zalo_part1.statics.Status;
import com.example.demo_zalo_part1.statics.Type;

import java.time.LocalDate;

public class MessageResponse {

    ConversationResponse conversationId;
    UserResponse sender;
    Type type;
    String contentText;
    String contentRickText;
    Boolean hasMentioned;
    Boolean deleted;
    Boolean edited;
    Integer replyToMessageId;
    Status status;
    LocalDate createdAt;
    LocalDate updatedAt;
}
