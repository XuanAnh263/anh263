package com.example.socialwave.repository;

import com.example.socialwave.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FriendRepository  extends JpaRepository<Friend, Integer> {
    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM friend  WHERE  (user_a=?1 or user_b=?1) and state like '%accepted%'  ", nativeQuery = true)
    public List<Friend> getAllFriend(int userId);

    //admin
    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM friend  WHERE   state like '%accepted%'  ", nativeQuery = true)
    public List<Friend> getAllFriendAccepted();


    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM friend  WHERE  user_a=?1 or user_b=?1", nativeQuery = true)
    public List<Friend> getAllListFriend(int userId);


    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM friend  WHERE user_b= ?1 and state like '%waiting%' ", nativeQuery = true)
    public List<Friend> getAllRequestFriend(int userId);


    @Transactional
    @Modifying
    @Query(value = "UPDATE friend SET state = ?2 WHERE fid = ?1", nativeQuery = true)
    void acceptFriendRequest(int id, String state);


}
