package com.example.kanban_management.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class BeanConfig {
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();//convert giữa moldel và entity
        objectMapper.registerModule(new JavaTimeModule()); //convert Date
        return objectMapper;
    }
}
