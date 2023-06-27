package com.example.homework_driver_2.service;

import com.example.homework_driver_2.model.respond.LevelResponse;
import com.example.homework_driver_2.statics.Level;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LevelService {
    public List<LevelResponse> getAllLevel() {
        List<LevelResponse> dtos = new ArrayList<>();
        dtos.add(new LevelResponse(Level.A.toString(), Level.A.name));
        dtos.add(new LevelResponse(Level.B.toString(), Level.B.name));
        dtos.add(new LevelResponse(Level.C.toString(), Level.C.name));
        dtos.add(new LevelResponse(Level.D.toString(), Level.D.name));
        dtos.add(new LevelResponse(Level.E.toString(), Level.E.name));
        dtos.add(new LevelResponse(Level.F.toString(), Level.F.name));
        return dtos;
    }
}
