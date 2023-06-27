package com.example.demolearnjpa.mapper;

import com.example.demolearnjpa.dto.WorkerDto;
import com.example.demolearnjpa.entity.Worker;

public class WorkerMapper {
    public static WorkerDto toWorkerDto(Worker worker) {
        return WorkerDto.builder()
                .id(worker.getId())
                .name(worker.getName())
                .email(worker.getEmail())
                .build();
    }
}
