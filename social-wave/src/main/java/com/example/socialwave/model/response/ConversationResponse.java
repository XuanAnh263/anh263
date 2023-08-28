package com.example.socialwave.model.response;

import com.example.socialwave.statics.ConversationStatus;
import com.example.socialwave.statics.ConversationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationResponse {
    Long id;
    String name;
    String avatar;
    String description;
    ConversationType type;
    ConversationStatus status;
    LocalDateTime creatDateTime;
    LocalDateTime lastModifiedDateTime;
    Set<Long> userIds;
    Long ownerId;

}
