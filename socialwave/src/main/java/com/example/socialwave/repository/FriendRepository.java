package com.example.socialwave.repository;


import com.example.socialwave.entity.Friend;
import com.example.socialwave.entity.User;
import com.example.socialwave.security.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    boolean existsByUserIdAndFriendId(User userId, User friendId);
    @Query("SELECT f FROM Friend f WHERE f.userId = :user OR f.friendId = :user")
    List<User> findAllFriendsByUser(@Param("user") User user);

    @Query("SELECT f FROM Friend f WHERE f.userId = :user OR f.friendId = :user")
    List<UserDetails> findAllFriendsByCustomUserDetails(@Param("user") CustomUserDetails customUserDetails);

    @Query("SELECT f FROM Friend f WHERE f.userId = :user OR f.friendId = :user")
    List<UserDetails> findAllFriendsByUserId(@Param("user") CustomUserDetails customUserDetails);
    @Query("SELECT f FROM Friend f WHERE f.userId = :user OR f.friendId = :user")
    List<Friend> findByUserId(@Param("user") User friend);

    @Query("SELECT f FROM Friend f WHERE f.userId = :user OR f.friendId = :user")
    List<Friend> findByUserAndFriend(User user);

//    List<Friend> findByUserId(User userId);
}
