package com.example.socialwave.service;

import com.example.socialwave.model.dto.FriendDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendService {
    List<FriendDTO> getAllFriend(int userId);

    List<FriendDTO> getAllFriend();
    List<FriendDTO> getAllFriendAccepted();
    List<FriendDTO> getAllFriendAccepted(int userId);

    List<FriendDTO> getAllFriendRequest(int userId);

    void createFriendRequest(int userAId, int userBId);

    void acceptFriendRequest( int id);

    void deleteFriendRequest(int id);// đúng trong cả trường hợp gửi lời mời kết bạn và
    // trường hợp từ chối lời mời kết bạn
}
