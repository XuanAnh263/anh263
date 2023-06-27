package com.example.homework_driver_2.controller;

import com.example.homework_driver_2.model.request.RouteRequest;
import com.example.homework_driver_2.model.respond.RouteResponse;
import com.example.homework_driver_2.service.RouteService;
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

public class RouteController {
    RouteService routeService;


    @GetMapping("/routes")
    public String getRoutes(Model model) {
        List<RouteResponse> routeResponses = routeService.getAllRouteResponse();
        model.addAttribute("danhSachRoute", routeResponses);

        model.addAttribute("routeMuonTaoMoi", new RouteRequest());
        return "route-list";
    }

    @PostMapping("/routes")
    public String createNewRoute(@ModelAttribute("routeMuonTaoMoi") @Valid RouteRequest routeMuonTaoMoi) {
        routeService.saveRoute(routeMuonTaoMoi);
        return "redirect:/routes";
    }

    @GetMapping("/api/routes/{id}")
    public ResponseEntity<?> getRoute(@PathVariable int id) {
        return ResponseEntity.ok(routeService.findByIdVer2(id));
    }

    @PutMapping("/api/routes/{id}")
    public ResponseEntity<?> updateRoute(@PathVariable int id, @RequestBody @Valid RouteRequest routeRequest) {
        routeRequest.setId(id);
        routeService.updateRoute(routeRequest);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/api/routes/delete/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable int id) {
        routeService.deleteRoute(id);
        return ResponseEntity.ok(null);
    }
}
