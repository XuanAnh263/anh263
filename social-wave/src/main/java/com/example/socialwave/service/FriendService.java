package com.example.socialwave.service;

import com.example.socialwave.entity.Friend;
import com.example.socialwave.entity.User;
import com.example.socialwave.exception.BadRequestException;
import com.example.socialwave.exception.NotFoundException;
import com.example.socialwave.model.request.FriendRequest;
import com.example.socialwave.model.response.FriendResponse;
import com.example.socialwave.model.response.UserResponse;
import com.example.socialwave.repository.FriendRepository;
import com.example.socialwave.repository.UserRepository;
import com.example.socialwave.statics.FriendStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendService {
    UserRepository userRepository;
    FriendRepository friendRepository;
    ObjectMapper objectMapper;

    public List<FriendResponse> getAll() {
        List<Friend> friendList = friendRepository.findAll();
        if (!CollectionUtils.isEmpty(friendList)) {
            return friendList.stream().map(friend -> objectMapper.convertValue(friend, FriendResponse.class)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Optional<FriendResponse> addFriend(FriendResponse friendResponse) {
        User friendId = friendResponse.getFriendId();
        User userId = friendResponse.getUserId();

        if (userId == null || friendId == null) {
            return Optional.of(new FriendResponse(FriendStatus.FAILURE));
        }

        Optional<User> userOptional = userRepository.findById(userId.getId());
        Optional<User> friendOptional = userRepository.findById(friendId.getId());

        if (userOptional.isEmpty() || friendOptional.isEmpty()) {
            return Optional.of(new FriendResponse(FriendStatus.FAILURE));
        }

        Friend friendShip = new Friend();
        friendShip.setUserId(userOptional.get());
        friendShip.setFriendId(friendOptional.get());

        friendRepository.save(friendShip);

        return Optional.of(new FriendResponse(FriendStatus.SUCCESS));
    }


}
