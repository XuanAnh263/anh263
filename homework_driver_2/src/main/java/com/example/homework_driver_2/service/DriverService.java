package com.example.homework_driver_2.service;

import com.example.homework_driver_2.entity.Driver;
import com.example.homework_driver_2.model.request.DriverCreateRequest;
import com.example.homework_driver_2.model.request.DriverUpdateRequest;
import com.example.homework_driver_2.model.respond.DriverResponse;
import com.example.homework_driver_2.validation.ObjectNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DriverService {
    ObjectMapper objectMapper;

    private static final List<Driver> driverList = new ArrayList<>();
    private static int AUTO_ID = 10001;

    public List<DriverResponse> getAllDriver() {
        List<DriverResponse> driverResponses = new ArrayList<>();
        driverList.forEach(driver -> {
            DriverResponse driverResponse = objectMapper.convertValue(driver, DriverResponse.class);
            driverResponses.add(driverResponse);
        });
        return driverResponses;
    }

    public void saveDriver(DriverCreateRequest driverCreateRequest) {
        Driver driver = objectMapper.convertValue(driverCreateRequest, Driver.class);
        driver.setId(AUTO_ID);
        AUTO_ID++;
        driverList.add(driver);
    }

    public Object findByIdVer2(Integer id) {
        Optional<Driver> driverOptional = driverList
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        if (driverOptional.isEmpty()) {
            throw new ObjectNotFoundException("Không tìm thấy lái xe mang mã " + id);
        }
        Driver driver = driverOptional.get();
        return objectMapper.convertValue(driver, DriverResponse.class);
    }

    public void updateDriver(DriverUpdateRequest driverUpdateRequest) {
        driverList.forEach(driver -> {
            if (driver.getId() != driverUpdateRequest.getId()) return;
            driver.setName(driverUpdateRequest.getName());
            driver.setAddress(driverUpdateRequest.getAddress());
            driver.setPhone(driverUpdateRequest.getPhone());
            driver.setLevel(driverUpdateRequest.getLevel());
        });
    }

    public void deleteDriver(int id) {
        driverList.removeIf(driver -> driver.getId() == id);
    }

    public Driver findDriverByid(int id) {
        for (Driver driver : driverList) {
            if (driver.getId() == id) return driver;
        }
        return null;
    }
}
