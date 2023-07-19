package com.example.socialwave.controller;

import com.example.socialwave.entity.User;
import com.example.socialwave.exception.UserNotFoundException;
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
//    @PostMapping("/request/{userId}/{friendId}")
//    public ResponseEntity<FriendResponse> addFriend( @RequestBody FriendRequest request) {
////        request.setUserId();
////        request.setFriendId(friendId);
//        User userId = request.setUserId(u);
//
//        Optional<FriendResponse> response = friendService.addFriend();
//        if (response.isPresent()) {
//            FriendResponse friendResponse = response.get();
//            return ResponseEntity.status(HttpStatus.CREATED).body(friendResponse);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
//
//    }


//    @PostMapping("/request/{user_id}")
//    public ResponseEntity<?> addFriendRequest(@PathVariable("user_id") Long userId) {
//        try {
//            friendService.addFriend(userId);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Friend request sent successfully.");
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.badRequest().body("User not found.");
//        }
//    }
}
