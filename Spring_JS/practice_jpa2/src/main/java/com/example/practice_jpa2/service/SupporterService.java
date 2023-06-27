package com.example.practice_jpa2.service;

import com.example.practice_jpa2.entity.Course;
import com.example.practice_jpa2.entity.Supporter;
import com.example.practice_jpa2.model.request.CreateSupporterRequest;
import com.example.practice_jpa2.model.request.UpdateSupporterRequest;
import com.example.practice_jpa2.repository.CourseRepository;
import com.example.practice_jpa2.repository.SupporterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
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
public class SupporterService {
    SupporterRepository supporterRepository;

    ObjectMapper objectMapper;

    Faker faker;
    CourseRepository courseRepository;

    public Page<Supporter> getAllSPPage(Integer page, Integer pageSize) {
        Pageable pageRequest = PageRequest.of(page - 1, pageSize);
        return supporterRepository.findAll(pageRequest);
    }

    public List<Supporter> getSupporter(){
        return supporterRepository.findAll();
    }

    public void deleteSp(Integer id) {
        Optional<Supporter> supporterOptional = supporterRepository.findById(id);
        if (supporterOptional.isPresent()) {
            Supporter supporter = supporterOptional.get();
            for (Course course : courseRepository.findAll()) {
                if (course.getSupporter().getId().equals(supporter.getId())){
                    courseRepository.delete(course);
                }
            }
            supporterRepository.delete(supporter);
        }
    }

    public Supporter save(CreateSupporterRequest createSupporterRequest) {
        if (supporterRepository.existsByEmail(createSupporterRequest.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        Supporter supporter=objectMapper.convertValue(createSupporterRequest,Supporter.class);
        supporter.setAvatar(faker.company().logo());
        return supporterRepository.save(supporter);
    }

    public Supporter getSupporterId(Integer id) {
        Optional<Supporter> optional = supporterRepository.findById(id);
        return optional.orElse(null);
    }

    public void update(Integer id, UpdateSupporterRequest update) {
        Supporter supporter = supporterRepository.findById(id).orElse(null);
        if (supporter == null){
            return;
        }
        supporter.setName(update.getName());
        supporter.setPhone(update.getPhone());
        supporter.setAvatar(faker.company().logo());
        supporterRepository.save(supporter);
    }
}
