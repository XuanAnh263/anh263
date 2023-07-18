package com.example.socialwave.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@ToString

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaFileRequest {


    PostRequest post;
    FileRequest file;
    UserRequest creatorId;
}
