package com.example.practive_jpa.repository;

import com.example.practive_jpa.entity.Course;
import com.example.practive_jpa.entity.Supporter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupporterRepository extends JpaRepository<Supporter, Integer> {
    Supporter getSupporterByCourses(Course course);
}
