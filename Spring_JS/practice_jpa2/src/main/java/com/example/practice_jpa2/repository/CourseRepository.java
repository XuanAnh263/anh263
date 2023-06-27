package com.example.practice_jpa2.repository;

import com.example.practice_jpa2.entity.Course;
import com.example.practice_jpa2.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> findByType(Pageable pageable, String type);

    List<Course> findByType(String type);
    List<Course> findByTopics(Topic thisTopic);
}