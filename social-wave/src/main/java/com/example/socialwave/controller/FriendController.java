package com.example.socialwave.controller;

import com.example.socialwave.model.request.FriendRequest;
import com.example.socialwave.model.response.FriendResponse;
import com.example.socialwave.service.FriendService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/friends")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendController {
    FriendService friendService;



    //TODO api add friend
    @PostMapping("/request/{userId}/{friendId}")
    public ResponseEntity<FriendResponse> addFriend(@PathVariable Long userId, @PathVariable Long friendId, @RequestBody FriendRequest request) {
        request.setUserId(userId);
        request.setFriendId(friendId);

        Optional<FriendResponse> response = friendService.addFriend(request);
        if (response.isPresent()) {
            FriendResponse friendResponse = response.get();
            return ResponseEntity.status(HttpStatus.CREATED).body(friendResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }
}
