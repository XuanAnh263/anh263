package com.example.practive_jpa.repository;

import com.example.practive_jpa.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
}
