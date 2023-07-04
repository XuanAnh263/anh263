package com.example.demo_zalo_part1.repository;

import com.example.demo_zalo_part1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
