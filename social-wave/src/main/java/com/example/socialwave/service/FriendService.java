package com.example.socialwave.service;

import com.example.socialwave.entity.Friend;
import com.example.socialwave.entity.User;
import com.example.socialwave.model.request.FriendRequest;
import com.example.socialwave.model.response.FriendResponse;
import com.example.socialwave.repository.FriendRepository;
import com.example.socialwave.repository.UserRepository;
import com.example.socialwave.statics.FriendStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendService {
    UserRepository userRepository;
    FriendRepository friendRepository;

    public Optional<FriendResponse> addFriend(FriendRequest request) {
        Long friendId = request.getFriendId();
        Long userId = request.getUserId();

//        if (friendRepository.existsByUserIdAndFriendId(userId, friendId)) {
//            return new FriendResponse("These users are already friends");
//        }
//
//        User user = userRepository.findById(userId).orElseThrow(() -> (new  RuntimeException("User not found")));
//        User friend = userRepository.findById(friendId).orElseThrow(() -> (new  RuntimeException("User not found")));
//
        if (userId == null || friendId == null) {
            return Optional.empty();
        }

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<User> friendOptional = userRepository.findById(friendId);

        if (userOptional.isEmpty() || friendOptional.isEmpty()) {
            return Optional.empty();
        }
        Friend friendShip = new Friend();
        friendShip.setUserId(userOptional.get());
        friendShip.setFriendId(friendOptional.get());

        friendRepository.save(friendShip);

        return Optional.of(new FriendResponse(userId,FriendStatus.SUCCESS));

    }


}
