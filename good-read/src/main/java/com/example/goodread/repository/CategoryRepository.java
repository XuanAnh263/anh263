package com.example.goodread.repository;


import com.example.goodread.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    List<Category> findAllByNameContainingIgnoreCase(String nameCategory);

    Optional<Category> findAllByName(String nameCategory);
}