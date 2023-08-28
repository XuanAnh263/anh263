package com.example.goodread.service;

import com.example.goodread.entity.Role;
import com.example.goodread.exception.BadRequestException;
import com.example.goodread.model.request.RoleRequest;
import com.example.goodread.repository.*;
import com.example.goodread.repository.custom.BookCustomRepository;
import com.example.goodread.statics.Roles;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    BookRepository bookRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    ReadingBookRepository readingBookRepository;
    BookOfChallengeRepository bookOfChallengeRepository;
    ObjectMapper objectMapper;
    BookCustomRepository bookCustomRepository;
    RoleRepository roleRepository;

    public List<Role> findAllRole() {
        List<Role> result = roleRepository.findAll();
        return result;
    }

    public void createRole(RoleRequest request) throws BadRequestException {
        Optional<Role> roleOptional = roleRepository.findByName(Roles.valueOf(request.getName()));
        if (roleOptional.isPresent()) {
            throw new BadRequestException();
        }
        Role role = Role.builder()
                .name(Roles.valueOf(request.getName()))
                .build();
        roleRepository.save(role);
    }
}

