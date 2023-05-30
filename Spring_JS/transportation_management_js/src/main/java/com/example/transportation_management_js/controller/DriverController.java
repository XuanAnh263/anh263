package com.example.transportation_management_js.controller;

import com.example.transportation_management_js.model.request.DriverCreateRequest;
import com.example.transportation_management_js.model.request.DriverUpdateRequest;
import com.example.transportation_management_js.model.response.DriverLevelResponse;
import com.example.transportation_management_js.model.response.DriverResponse;
import com.example.transportation_management_js.service.DriverLevelService;
import com.example.transportation_management_js.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping
public class DriverController {
    DriverService driverService;
    DriverLevelService driverLevelService;

    @GetMapping("/drivers")
    public String getDrivers(Model model) {
        List<DriverResponse> driverResponses = driverService.getAllDrivers();
        model.addAttribute("DriverList", driverResponses);

        model.addAttribute("CreateDriver", new DriverCreateRequest());
        return "driver-list";

    }


    @PostMapping("/drivers")
    public String createNewDriver(@ModelAttribute("CreateDriver") @Valid DriverCreateRequest CreateDriver) {
        driverService.saveDriver(CreateDriver);
        return "redirect:/drivers";
    }

    @ModelAttribute("levelData")
    public List<DriverLevelResponse> getAllLevel() {
        return driverLevelService.getAll();
    }

    @GetMapping("/api/drivers/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(driverService.findById(id));
    }

    @PutMapping("/api/drivers/{id}")
    public  ResponseEntity<?> updateDriver(@PathVariable Integer id, @RequestBody @Valid DriverUpdateRequest driverUpdateRequest) {
        driverUpdateRequest.setId(id);
        driverLevelService.getAll();
        driverService.updateDriver(driverUpdateRequest);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/api/drivers/delete/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable int id) {
        driverService.delete(id);
        return ResponseEntity.ok(null);
    }





}
