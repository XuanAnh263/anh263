package com.example.transportation_management_js.controller;


import com.example.transportation_management_js.model.request.AssignementRequest;
import com.example.transportation_management_js.model.response.AssignementResponse;
import com.example.transportation_management_js.service.AssignmentService;
import com.example.transportation_management_js.service.DriverService;
import com.example.transportation_management_js.service.RouteService;
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
public class AssignmentController {
    AssignmentService assignmentService;
    DriverService driverService;
    RouteService routeService;

    @GetMapping("/assignments")
    public String getAssignments(Model model) {
        List<AssignementResponse> assignementResponses = assignmentService.getAllAssigments();
        model.addAttribute("danhSachPhanCong", assignementResponses);

        model.addAttribute("taoPhanCongMoi", new AssignementRequest());

        getDriverAndRoute(model);

        return "assignment-list";
    }

    @PostMapping("/assignments")
    public String createNewAssignment(@ModelAttribute("taoPhanCongMoi") @Valid AssignementRequest taoPhanCongMoi) {
        assignmentService.saveAssignment(taoPhanCongMoi);
        return "redirect:/assignments";
    }

    @GetMapping("/api/assignments/{id}")
    public ResponseEntity<?> getAssignment(@PathVariable int id) {
        return ResponseEntity.ok(assignmentService.findByIdVer2(id));
    }

    @PutMapping("/api/assignments/{id}")
    public ResponseEntity<?> updateAssignment(@PathVariable int id, @RequestBody @Valid AssignementRequest assignementRequest) {
        assignementRequest.setId(id);
        assignmentService.updateAssignment(assignementRequest);
        return ResponseEntity.ok(null);
    }

    private void getDriverAndRoute(Model model) {
        model.addAttribute("danhSachDriver", driverService.getAllDriver());
        model.addAttribute("danhSachRoute", routeService.getAllRouteResponse());
    }
}
