package com.example.transportation_management_js.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();//convert giữa moldel và entity
        objectMapper.registerModule(new JavaTimeModule()); //convert Date
        return objectMapper;
    }
}
