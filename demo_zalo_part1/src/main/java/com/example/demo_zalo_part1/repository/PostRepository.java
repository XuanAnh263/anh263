package com.example.demo_zalo_part1.repository;

import com.example.demo_zalo_part1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
