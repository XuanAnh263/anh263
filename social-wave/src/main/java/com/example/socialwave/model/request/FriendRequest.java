package com.example.socialwave.model.request;

import com.example.socialwave.statics.FriendStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendRequest {


    Long userId;
    Long friendId;
    FriendStatus status;

}
