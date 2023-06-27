package com.example.practice_jpa2.service;

import com.example.practice_jpa2.entity.Course;
import com.example.practice_jpa2.entity.Supporter;
import com.example.practice_jpa2.entity.Topic;
import com.example.practice_jpa2.model.request.UpsertCourseRequest;
import com.example.practice_jpa2.repository.CourseRepository;
import com.example.practice_jpa2.repository.SupporterRepository;
import com.example.practice_jpa2.repository.TopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseService {
    CourseRepository courseRepository;

    TopicRepository topicRepository;

    SupporterRepository supporterRepository;

    ObjectMapper objectMapper;

    Faker faker;

    public Page<Course> getAllCourse(Integer page, Integer pageSize) {
        Pageable pageRequest = PageRequest.of(page - 1, pageSize);
        return courseRepository.findAll(pageRequest);
    }

    public Page<Course> getAllCourse(Integer page, Integer pageSize, String type) {
        Pageable pageRequest = PageRequest.of(page - 1, pageSize);
        return courseRepository.findByType(pageRequest, type);
    }

    public List<Course> getAllCourse(String type) {
        return courseRepository.findByType(type);
    }

    public Course getCourseDetail(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found course with id = " + id);
                });
    }

    public void save(UpsertCourseRequest courseRequest) {
        Set<Topic> topics=new LinkedHashSet<>();
        Supporter supporter=supporterRepository.findById(courseRequest.getSupporterId()).orElse(null);
        for (Integer topic:courseRequest.getTopicIds()) {
            topics.add(topicRepository.findById(topic).orElse(null));
        }
        Course course=objectMapper.convertValue(courseRequest,Course.class);
        course.setThumbnail(faker.company().logo());
        course.setTopics(topics);
        course.setSupporter(supporter);
        courseRepository.save(course);
    }

    public Course findById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void updateCourse(Integer id, UpsertCourseRequest courseRequest) {
        Course course= courseRepository.findById(id).orElse(null);
        Set<Topic> topics=new LinkedHashSet<>();
        Supporter supporter=supporterRepository.findById(courseRequest.getSupporterId()).orElse(null);
        for (Integer topic:courseRequest.getTopicIds()) {
            topics.add(topicRepository.findById(topic).orElse(null));
        }
        if (course!=null){
            course.setName(courseRequest.getName());
            course.setDescription(courseRequest.getDescription());
            course.setType(courseRequest.getType());
            course.setSupporter(supporter);
            course.setTopics(topics);
            courseRepository.save(course);
        }
    }

    public void deleteCourse(Integer id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setTopics(null);
            course.setSupporter(null);
            courseRepository.delete(course);
        }
    }
}

