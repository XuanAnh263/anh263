package com.example.practice_jpa2.service;

import com.example.practice_jpa2.entity.Course;
import com.example.practice_jpa2.entity.Topic;
import com.example.practice_jpa2.repository.CourseRepository;
import com.example.practice_jpa2.repository.TopicRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TopicService {
    TopicRepository topicRepository;

    CourseRepository courseRepository;

    public List<Topic> getAllTopic(){
        return topicRepository.findAll();
    }

    public Page<Topic> getAllTopicPage(Integer page, Integer pageSize) {
        Pageable pageRequest = PageRequest.of(page - 1, pageSize);
        return topicRepository.findAll(pageRequest);
    }

    public Topic save(String name)throws RuntimeException {
        if (topicRepository.existsByName(name)){
            throw new RuntimeException("Name already exist");
        }
        Topic topic=new Topic();
        topic.setName(name);
        return topicRepository.save(topic);
    }

    public void deleteTopic(Integer id) {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if (topicOptional.isPresent()) {
            Topic thisTopic = topicOptional.get();
            List<Course> courses = courseRepository.findByTopics(thisTopic);
            for (Course course : courses) {
                course.getTopics().remove(thisTopic);
                courseRepository.save(course);
            }
            topicRepository.delete(thisTopic);
        }
    }

    public Topic getTopic(Integer id) {
        return topicRepository.findById(id).orElse(null);
    }

    public Topic update(Integer id, String name) throws RuntimeException{
        Topic topic = getTopic(id);
        for (Topic t:topicRepository.findAll()) {
            if (t.getName().equals(name)){
                throw new RuntimeException("Name already exist");
            }
        }
        topic.setName(name);
        return topicRepository.save(topic);
    }

}
