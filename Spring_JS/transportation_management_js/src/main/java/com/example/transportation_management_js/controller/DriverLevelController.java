package com.example.transportation_management_js.controller;

import com.example.transportation_management_js.model.response.DriverLevelResponse;
import com.example.transportation_management_js.service.DriverLevelService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("api/driverLevels")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DriverLevelController {
    DriverLevelService driverLevelService;
    @GetMapping
    public List<DriverLevelResponse> getAll() {
        return driverLevelService.getAll();
    }
}
