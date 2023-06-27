package com.example.homework_driver_2.controller;

import com.example.homework_driver_2.model.respond.LevelResponse;
import com.example.homework_driver_2.service.LevelService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class LevelController {
    LevelService levelService;

    @GetMapping
    public List<LevelResponse> getAll() {
        return levelService.getAllLevel();
    }

}
