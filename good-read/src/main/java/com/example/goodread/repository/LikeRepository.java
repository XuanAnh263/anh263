package com.example.goodread.repository;


import com.example.goodread.entity.Like;
import com.example.goodread.entity.Review;
import com.example.goodread.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndReview(User user, Review review);

    void deleteAllByReview(Review review);
}