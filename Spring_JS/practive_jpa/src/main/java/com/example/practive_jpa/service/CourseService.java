package com.example.practive_jpa.service;

import com.example.practive_jpa.entity.Course;
import com.example.practive_jpa.entity.Supporter;
import com.example.practive_jpa.repository.CourseRepository;
import com.example.practive_jpa.repository.SupporterRepository;
import com.example.practive_jpa.statics.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService {

    CourseRepository courseRepository;

    SupporterRepository supporterRepository;


    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public List<Course> getByType(Type onlab) {
        return courseRepository.getCourseByType(onlab);
    }

    public Supporter getSupporterByCourseid(Integer id) {
        Optional<Course> courseOptional=courseRepository.findById(id);
        Course course = courseOptional.get();
        return supporterRepository.getSupporterByCourses(course);
    }

    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).get();
    }
}
