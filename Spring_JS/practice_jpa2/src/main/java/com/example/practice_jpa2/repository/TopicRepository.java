package com.example.practice_jpa2.repository;

import com.example.practice_jpa2.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Boolean existsByName(String name);

}
