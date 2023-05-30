package com.example.transportation_management_js.service;

import com.example.transportation_management_js.entity.Driver;
import com.example.transportation_management_js.exceptionhandling.exception.ObjectNotFoundException;
import com.example.transportation_management_js.model.request.DriverCreateRequest;
import com.example.transportation_management_js.model.request.DriverUpdateRequest;
import com.example.transportation_management_js.model.response.DriverResponse;
import com.example.transportation_management_js.statics.DriverLevel;
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

    List<Driver> drivers;
    private static int AUTO_ID = 10000;

    public List<DriverResponse> getAllDrivers() {
        List<DriverResponse> rs = new ArrayList<>();
        for (int i = 0; i < drivers.size(); i++) {
            Driver driver = drivers.get(i);

            DriverResponse driverResponse = objectMapper.convertValue(driver, DriverResponse.class);
            rs.add(driverResponse);
        }
        return rs;
    }

    public void saveDriver(DriverCreateRequest driverCreateRequest) {
        Driver driver = objectMapper.convertValue(driverCreateRequest, Driver.class);
        driver.setId(AUTO_ID);
        drivers.add(driver);
        AUTO_ID++;
    }

    public void delete(int id) {
        drivers.removeIf(driver -> driver.getId() == id);
    }

    public DriverResponse findById(Integer id) {
        Optional<Driver> driverOptional = drivers
                .stream()
                .filter(driver -> driver.getId() == id)
                .findFirst();
        if (driverOptional.isEmpty()) {
            throw new ObjectNotFoundException("Cannot find driver with code " + id);
        }
        Driver driver = driverOptional.get();
        return objectMapper.convertValue(driver, DriverResponse.class);

    }

    public void updateDriver(DriverUpdateRequest driverUpdateRequest) {
        drivers.forEach(driver -> {
            if (driver.getId() != driverUpdateRequest.getId()) return;
            driver.setName(driverUpdateRequest.getName());
            driver.setAddress(driverUpdateRequest.getAddress());
            driver.setPhone(driverUpdateRequest.getPhone());
            driver.setDriverLevel(driverUpdateRequest.getDriverLevel());

        });
    }
}
