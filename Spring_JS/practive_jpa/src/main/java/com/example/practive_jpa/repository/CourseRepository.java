package com.example.practive_jpa.repository;

import com.example.practive_jpa.entity.Course;
import com.example.practive_jpa.statics.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> getCourseByType(Type type);
}
