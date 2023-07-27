package com.example.socialwave.model.request;

import com.example.socialwave.entity.Conversation;
import com.example.socialwave.entity.Sticker;
import com.example.socialwave.entity.User;
import com.example.socialwave.statics.MessageStatus;
import com.example.socialwave.statics.MessageType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//@JsonDeserialize(using = ConversationDeserializer.class)
public class MessageRequest {

    Conversation conversationId;


    User senderId;

    Sticker stickerId;


    MessageType type;

    @NotBlank(message = "Content text is required")
            @Size(max = 1000, message = "Content text must not exceed 1000 characters")
    String contentText;

    @NotBlank(message = "Content rick text is required")
    @Size(max = 5000, message = "Content rick text must not exceed 5000 characters")
    String contentRickText;

    Boolean mentioned;

    Boolean deleted;

    Boolean edited;

    Long replyToMessageId;


    MessageStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Creat date time is required")
    LocalDateTime createdDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Update is required")
    LocalDateTime lastModifiedDateTime;
}
