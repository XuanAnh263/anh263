package com.example.socialwave.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest extends BaseRequest{


    UserRequest user;
    String caption;
    String contentMedia;
    Long likeCount;
    Long commentCount;

}
