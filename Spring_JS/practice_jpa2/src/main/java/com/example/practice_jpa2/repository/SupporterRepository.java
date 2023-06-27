package com.example.practice_jpa2.repository;


import com.example.practice_jpa2.entity.Supporter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupporterRepository extends JpaRepository<Supporter, Integer> {
    boolean existsByEmail(String email);

}