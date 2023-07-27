package com.example.socialwave.model.response;

import com.example.socialwave.entity.File;
import com.example.socialwave.entity.Message;
import com.example.socialwave.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageFileResponse {

    Long id;

    Message message;

    File file;

    User creatorId;
}
