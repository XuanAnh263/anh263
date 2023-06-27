package com.example.homework_driver_2.service;

import com.example.homework_driver_2.entity.Assignement;
import com.example.homework_driver_2.entity.Driver;
import com.example.homework_driver_2.entity.Route;
import com.example.homework_driver_2.model.request.AssignementRequest;
import com.example.homework_driver_2.model.respond.AssignementResponse;
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

public class AssignmentService {
    ObjectMapper objectMapper;
    DriverService driverService;
    RouteService routeService;

    private List<Assignement> assignements;
    private static int AUTO_ID = 1;


    public List<AssignementResponse> getAllAssigments() {
        List<AssignementResponse> assignementResponses = new ArrayList<>();
        assignements.forEach(assignement -> {
            AssignementResponse assignementResponse = new AssignementResponse();
            assignementResponse.setId(assignement.getId());
            assignementResponse.setDriverId(assignement.getDriver().getId());
            assignementResponse.setDriverName(assignement.getDriver().getName());
            assignementResponse.setRouteId(assignement.getRoute().getId());
            assignementResponse.setRouteName(assignement.getRoute().getName());
            assignementResponse.setNumberOfTurn(assignement.getNumberOfTurn());
            assignementResponse.setDateOfAssignment(assignement.getDateOfAssignment());

            assignementResponses.add(assignementResponse);
        });
        return assignementResponses;
    }

    public void saveAssignment(AssignementRequest assignementRequest) {
        Driver driver = driverService.findDriverByid(assignementRequest.getDriverId());
        Route route = routeService.findRouteById(assignementRequest.getRouteId());
        Assignement assignement = new Assignement(AUTO_ID, driver, route, assignementRequest.getNumberOfTurn(), assignementRequest.getDateOfAssignment());
        AUTO_ID++;
        assignements.add(assignement);

    }

    public Object findByIdVer2(int id) {
        Optional<Assignement> assignementOptional = assignements
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        Assignement assignement = assignementOptional.get();

        AssignementResponse assignementResponse = new AssignementResponse();
        assignementResponse.setId(assignement.getId());
        assignementResponse.setDriverId(assignement.getDriver().getId());
        assignementResponse.setDriverName(assignement.getDriver().getName());
        assignementResponse.setRouteId(assignement.getRoute().getId());
        assignementResponse.setRouteName(assignement.getRoute().getName());
        assignementResponse.setNumberOfTurn(assignement.getNumberOfTurn());
        assignementResponse.setDateOfAssignment(assignement.getDateOfAssignment());

        return assignementResponse;
    }

    public Assignement findById(int id) {
        for (Assignement assignment : assignements) {
            if (assignment.getId() == id) return assignment;
        }
        return null;
    }

    public void updateAssignment(AssignementRequest assignmentRequest) {
        Assignement assignement = findById(assignmentRequest.getId());
        Driver driver = driverService.findDriverByid(assignmentRequest.getDriverId());
        Route route = routeService.findRouteById(assignmentRequest.getRouteId());

        assignement.setDriver(driver);
        assignement.setRoute(route);
        assignement.setNumberOfTurn(assignmentRequest.getNumberOfTurn());
        assignement.setDateOfAssignment(assignmentRequest.getDateOfAssignment());

    }
}
