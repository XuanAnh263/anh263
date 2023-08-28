package com.example.socialwave.repository;


import com.example.socialwave.entity.Friend;
import com.example.socialwave.entity.User;

import com.example.socialwave.statics.FriendStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    boolean existsByUserAndFriend(User user, User friend);

    @Query("SELECT f FROM Friend f WHERE f.user = :user OR f.friend = :user")
    List<Friend> findByUserAndFriend(User user);


    List<User> findAllByUser(User user);


}
