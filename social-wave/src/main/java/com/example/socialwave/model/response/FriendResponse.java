package com.example.socialwave.model.response;

import com.example.socialwave.statics.FriendStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendResponse {

    Long userId;
//    UserResponse friend;
    FriendStatus status;
    String message;

    public FriendResponse(Long userId, FriendStatus status) {
        this.userId = userId;
        this.status = status;
    }
}
