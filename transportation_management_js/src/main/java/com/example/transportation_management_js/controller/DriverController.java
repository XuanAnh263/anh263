package com.example.transportation_management_js.controller;

import com.example.transportation_management_js.model.request.DriverCreateRequest;
import com.example.transportation_management_js.model.request.DriverUpdateRequest;
import com.example.transportation_management_js.model.response.DriverResponse;
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

    @GetMapping("/drivers")
    public String getAllDrivers(Model model) {
        List<DriverResponse> driverResponses = driverService.getAllDriver();
        model.addAttribute("danhSachDriver", driverResponses);

        model.addAttribute("driverMuonTaoMoi", new DriverCreateRequest());

        return "driver-list";
    }

    @PostMapping("/drivers")
    public String createNewDriver(@ModelAttribute("driverMuonTaoMoi") @Valid DriverCreateRequest driverCreateRequest) {
        driverService.saveDriver(driverCreateRequest);
        return "redirect:/drivers";
    }


    //API
    @GetMapping("/api/drivers/{id}")
    public ResponseEntity<?> getDriver(@PathVariable Integer id) {
        return ResponseEntity.ok(driverService.findByIdVer2(id));
    }

    @PutMapping("/api/drivers/{id}")
    public ResponseEntity<?> updateDriver(@PathVariable int id, @RequestBody @Valid DriverUpdateRequest driverUpdateRequest) {
        driverUpdateRequest.setId(id);
        driverService.updateDriver(driverUpdateRequest);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/api/drivers/delete/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable int id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok(null);
    }





}
