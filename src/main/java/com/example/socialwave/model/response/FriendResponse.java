package com.example.socialwave.model.response;

import com.example.socialwave.entity.User;
import com.example.socialwave.statics.FriendStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendResponse {
    User userId;
    User friendId;
    FriendStatus status;
    String message;

//    public FriendResponse(FriendStatus status, String message) {
//        this.status = status;
//        this.message = message;
//    }

    public FriendResponse(FriendStatus status) {
        this.status = status;
    }
}
