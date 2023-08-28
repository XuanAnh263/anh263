package com.example.goodread.repository;


import com.example.goodread.entity.Comment;
import com.example.goodread.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByReview(Review review);
}