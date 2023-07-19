package com.example.socialwave.repository;


import com.example.socialwave.entity.Friend;
import com.example.socialwave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    boolean existsByUserIdAndFriendId(User userId, User friendId);

    List<Friend> findAllFriends(User user);
    Friend findByUserAndFriend(User user, User friend);
}
