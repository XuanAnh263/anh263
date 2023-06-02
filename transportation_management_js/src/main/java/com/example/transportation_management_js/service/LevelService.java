package com.example.transportation_management_js.service;

import com.example.transportation_management_js.model.response.LevelResponse;
import com.example.transportation_management_js.statics.Level;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LevelService {
    public List<LevelResponse> getAllLevel() {
        List<LevelResponse> dtos = new ArrayList<>();
        dtos.add(new LevelResponse(Level.A.toString(), Level.A.name()));
        dtos.add(new LevelResponse(Level.B.toString(), Level.B.name()));
        dtos.add(new LevelResponse(Level.C.toString(), Level.C.name()));
        dtos.add(new LevelResponse(Level.D.toString(), Level.D.name()));
        dtos.add(new LevelResponse(Level.E.toString(), Level.E.name()));
        dtos.add(new LevelResponse(Level.F.toString(), Level.F.name()));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_A.getName(), DriverLevel.HANG_A.name));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_B.getName(), DriverLevel.HANG_B.name));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_C.getName(), DriverLevel.HANG_C.name));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_D.getName(), DriverLevel.HANG_D.name));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_E.getName(), DriverLevel.HANG_E.name));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_F.getName(), DriverLevel.HANG_F.name));
        return dtos;
    }
}
