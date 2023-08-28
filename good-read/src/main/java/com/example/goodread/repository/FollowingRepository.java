package com.example.goodread.repository;


import com.example.goodread.entity.Following;
import com.example.goodread.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowingRepository extends JpaRepository<Following, Long> {
    Optional<Following> findByUserRequestAndUserAccept(User userRequest, User userAccept);

}