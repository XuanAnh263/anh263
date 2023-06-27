package com.example.practive_jpa;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PractiveJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PractiveJpaApplication.class, args);
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
