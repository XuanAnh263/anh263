package com.example.homework_driver_2.service;

import com.example.homework_driver_2.entity.Route;
import com.example.homework_driver_2.model.request.RouteRequest;
import com.example.homework_driver_2.model.respond.RouteResponse;
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

public class RouteService {
    ObjectMapper objectMapper;
    List<Route> routes;
    private static int AUTO_ID = 100;

    public List<RouteResponse> getAllRouteResponse() {
        List<RouteResponse> routeResponses = new ArrayList<>();
        routes.forEach(route -> {
            RouteResponse routeResponse = objectMapper.convertValue(route, RouteResponse.class);
            routeResponses.add(routeResponse);
        });
        return routeResponses;
    }


    public void saveRoute(RouteRequest routeMuonTaoMoi) {
        Route route = objectMapper.convertValue(routeMuonTaoMoi, Route.class);
        route.setId(AUTO_ID);
        AUTO_ID++;
        routes.add(route);
    }

    public Object findByIdVer2(int id) {
        Optional<Route> routeOptional = routes
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst();

        Route route = routeOptional.get();
        return objectMapper.convertValue(route, RouteResponse.class);
    }

    public void updateRoute(RouteRequest routeRequest) {
        routes.forEach(route -> {
            if (route.getId() != routeRequest.getId()) return;
            route.setName(routeRequest.getName());
            route.setDistance(routeRequest.getDistance());
            route.setNumberOfStop(routeRequest.getNumberOfStop());
        });
    }

    public void deleteRoute(int id) {
        routes.removeIf(route -> route.getId() == id);
    }

    public Route findRouteById(int id) {
        for (Route route : routes) {
            if (route.getId() == id) return route;
        }
        return null;
    }
}
