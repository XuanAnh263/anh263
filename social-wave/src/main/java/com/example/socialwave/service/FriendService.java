package com.example.socialwave.service;

import com.example.socialwave.entity.Friend;
import com.example.socialwave.entity.User;
import com.example.socialwave.exception.BadRequestException;
import com.example.socialwave.exception.NotFoundException;
import com.example.socialwave.model.request.FriendRequest;
import com.example.socialwave.model.request.UserRequest;
import com.example.socialwave.model.response.FriendResponse;
import com.example.socialwave.model.response.UserResponse;
import com.example.socialwave.repository.FriendRepository;
import com.example.socialwave.repository.UserRepository;
import com.example.socialwave.security.CustomUserDetails;
import com.example.socialwave.statics.FriendStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

//    public List<UserResponse> getAllFriend(CustomUserDetails user) {
//        List<Friend> friends = friendRepository.findAllFriends(user);
//        // Thực hiện các xử lý khác tại đây
//        // Ví dụ: Chuyển đổi danh sách Friend sang danh sách UserResponse và trả về
//
//        return friends.stream()
//                .map(friend -> mapToUserResponse(friend.getFriendId()))
//                .collect(Collectors.toList());
//    }
//    public List<UserResponse> getAllFriend(CustomUserDetails user) {
//       List<CustomUserDetails> friends = friendRepository.findAllFriends(user);
//
//        return friends.stream()
//                .map(Friend::getFriendId)
//                .map(this::mapToUserResponse)
//                .collect(Collectors.toList());
//    }

//API get list friend
//    public List<UserResponse> getAllFriend(CustomUserDetails user) {
//        List<CustomUserDetails> friends = friendRepository.findAllFriends(user);
//
//        return friends.stream()
//                .map(this::mapToUserResponse)
//                .collect(Collectors.toList());
//    }

    public List<UserResponse> getAllFriends() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            // Ép kiểu principal sang CustomUserDetails nếu đúng kiểu
            CustomUserDetails authenticatedUser = (CustomUserDetails) authentication.getPrincipal();

            // Lấy thông tin cần thiết từ CustomUserDetails
            Long userId = authenticatedUser.getId();
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                return Collections.emptyList();
            }
            List<Friend> friends = friendRepository.findByUserAndFriend(user.get());

            return friends.stream()
                    .map(Friend::getFriendId)
                    .map(this::mapToUserResponse)
                    .collect(Collectors.toList());

        }

        return Collections.emptyList();
    }

    private UserResponse mapToUserResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        userResponse.setDob(user.getDob());
        userResponse.setGender(user.getGender());
        userResponse.setAvatar(user.getAvatar());
        userResponse.setAddress(user.getAddress());
        userResponse.setPhone(user.getPhone());

        return userResponse;
    }

    public void sendFriendRequest(UserRequest userRequest, FriendRequest friendRequest) {

        User user = userRepository.findById(userRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        User friend = userRepository.findById(friendRequest.getFriendId())
                .orElseThrow(() -> new NotFoundException("Friend not found"));

        // Kiểm tra xem đã có yêu cầu kết bạn tương ứng đã được gửi hay chưa
        List<Friend> existingRequests = friendRepository.findByUserAndFriend(user);
        for (Friend request : existingRequests) {
            if (request.getFriendId().equals(friend)) {
                throw new IllegalArgumentException("Friend request already sent");
            }
        }

        // Tạo yêu cầu kết bạn mới
        Friend friends = new Friend(user, friend, FriendStatus.PENDING);
        friendRepository.save(friends);
    }


    public void acceptFriendRequest(Long requestId, Long userId) {
        Friend friendRequest = friendRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Friend request not found"));

        if (!friendRequest.getFriendId().getId().equals(userId)) {
            throw new IllegalArgumentException("User does not have permission to accept this request");
        }

        if (friendRequest.getStatus() != FriendStatus.PENDING) {
            throw new IllegalArgumentException("Friend request is not pending");
        }

        friendRequest.setStatus(FriendStatus.ACCEPT);
        friendRepository.save(friendRequest);
    }

    public void denyFriendRequest(Long requestId, Long userId) {
        Friend friendRequest = friendRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Friend request not found"));

        if (!friendRequest.getFriendId().getId().equals(userId)) {
            throw new IllegalArgumentException("User does not have permission to deny this request");
        }

        if (friendRequest.getStatus() != FriendStatus.PENDING) {
            throw new IllegalArgumentException("Friend request is not pending");
        }

        friendRequest.setStatus(FriendStatus.DENIED);
        friendRepository.save(friendRequest);
    }

    public void deleteFriend(Long id, User authenticateUser) {
        Friend friend = friendRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Friend not found"));
        if (!friend.getUserId().getId().equals(authenticateUser.getId())) {
            throw new IllegalArgumentException("ser does not have permission to delete this friend");
        }
        friendRepository.save(friend);
    }
}
