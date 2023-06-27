package com.example.homework_driver_2.config;

import com.example.homework_driver_2.entity.Role;
import com.example.homework_driver_2.entity.User;
import com.example.homework_driver_2.repository.RoleRepository;
import com.example.homework_driver_2.repository.UserRepository;
import com.example.homework_driver_2.statics.Roles;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Component
public class DatabaseIntializer implements CommandLineRunner {

    UserRepository userRepository;

    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role userRole=Role.builder().name(Roles.USER.toString()).build();

        Role adminRole=Role.builder().name(Roles.ADMIN.toString()).build();

        roleRepository.save(userRole);
        roleRepository.save(adminRole);


        User user=new User();
        user.setName("admin");
        user.setPassword(passwordEncoder.encode("admin123"));
        Set<Role> roles=new HashSet<>();
        roles.add(adminRole);
        user.setRoles(roles);
        userRepository.save(user);
    }
}
