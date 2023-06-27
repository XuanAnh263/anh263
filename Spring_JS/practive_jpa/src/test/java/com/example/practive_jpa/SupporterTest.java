package com.example.practive_jpa;

import com.example.practive_jpa.repository.SupporterRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor

public class SupporterTest {
    private SupporterRepository supporterRepository;
    private Faker faker;

    @Test
    void saveSupporter() {
        
    }
}
