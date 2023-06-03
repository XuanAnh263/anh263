package com.example.demolearnjpa;

import com.example.demolearnjpa.entity.Student;
import com.example.demolearnjpa.repository.StudentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SpringBootTest
public class StudentTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Faker faker;

    @Test
    void saveStudent() {
        for (int i = 0; i < 30; i++) {
            Student student = new Student(
                    null,
                    faker.name().fullName(),
                    faker.internet().emailAddress()
            );
            studentRepository.save(student);
        }
    }

    @Test
    void findAll_Pageable() {
        PageRequest request = PageRequest.of(0,5);
        Page<Student> page = studentRepository.findAll(request);
        page.getContent().forEach(System.out::println);

        PageRequest request1 = PageRequest.of(0,5);
        Page<Student> page1 = studentRepository.getAllStudent(request1);
        page1.getContent().forEach(System.out::println);
    }
    @Test
    void findAllByName() {
        PageRequest request = PageRequest.of(0,5);
        Page<Student> page = studentRepository.findByNameContainingIgnoreCaseNative("b", request);
        page.getContent().forEach(System.out::println);

        PageRequest request1 = PageRequest.of(0,5);
        Page<Student> page1 = studentRepository.findByNameContainingIgnoreCase("b", request1);
        page.getContent().forEach(System.out::println);

    }
}
