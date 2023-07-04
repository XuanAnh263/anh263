package com.example.demo_zalo_part1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DemoZaloPart1Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoZaloPart1Application.class, args);
    }

}
