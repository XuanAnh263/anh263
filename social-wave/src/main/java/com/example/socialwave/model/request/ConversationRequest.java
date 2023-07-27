package com.example.socialwave.model.request;


import com.example.socialwave.statics.ConversationStatus;
import com.example.socialwave.statics.ConversationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationRequest {

    Long id;
    @NotBlank(message = "Conversation name is required")
    String name;

    @NotBlank(message = "Conversation avatar is required")
    String avatar;

    @NotBlank(message = "Conversation description is required")
    String description;

    @NotNull(message = "Conversation type is required")
    ConversationType type;

    @NotNull(message = "Conversation status is required")
    ConversationStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @NotNull(message = "Creat date time is required")
    LocalDateTime creatDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Update is required")
    LocalDateTime lastModifiedDateTime;

    Set<Long> userIds;

    Long ownerId;
}
