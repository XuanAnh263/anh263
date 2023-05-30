package com.example.transportation_management_js.service;

import com.example.transportation_management_js.model.response.DriverLevelResponse;
import com.example.transportation_management_js.statics.DriverLevel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverLevelService {
    public List<DriverLevelResponse> getAll() {
        List<DriverLevelResponse> dtos = new ArrayList<>();
        dtos.add(new DriverLevelResponse(DriverLevel.HANG_A.toString(), DriverLevel.HANG_A.name()));
        dtos.add(new DriverLevelResponse(DriverLevel.HANG_B.toString(), DriverLevel.HANG_B.name()));
        dtos.add(new DriverLevelResponse(DriverLevel.HANG_C.toString(), DriverLevel.HANG_C.name()));
        dtos.add(new DriverLevelResponse(DriverLevel.HANG_D.toString(), DriverLevel.HANG_D.name()));
        dtos.add(new DriverLevelResponse(DriverLevel.HANG_E.toString(), DriverLevel.HANG_E.name()));
        dtos.add(new DriverLevelResponse(DriverLevel.HANG_F.toString(), DriverLevel.HANG_F.name()));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_A.getName(), DriverLevel.HANG_A.name));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_B.getName(), DriverLevel.HANG_B.name));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_C.getName(), DriverLevel.HANG_C.name));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_D.getName(), DriverLevel.HANG_D.name));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_E.getName(), DriverLevel.HANG_E.name));
//        dtos.add(new DriverLevelResponse(DriverLevel.HANG_F.getName(), DriverLevel.HANG_F.name));
        return dtos;
    }
}
