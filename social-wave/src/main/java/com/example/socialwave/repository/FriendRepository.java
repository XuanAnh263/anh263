package com.example.socialwave.repository;


import com.example.socialwave.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    boolean existsByUserIdAndFriendId(Long userId, Long friendId);
}
