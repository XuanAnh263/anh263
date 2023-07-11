package com.example.demo_zalo_part1.config;

import com.example.demo_zalo_part1.entity.Role;
import com.example.demo_zalo_part1.entity.User;
import com.example.demo_zalo_part1.repository.RoleRepository;
import com.example.demo_zalo_part1.repository.UserRepository;
import com.example.demo_zalo_part1.statics.Roles;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatabaseInitializer implements CommandLineRunner {

    UserRepository userRepository;

    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        Role userRole = Role.builder().name(Roles.USER).build();
//        Role adminRole = Role.builder().name(Roles.ADMIN).build();
//        roleRepository.save(userRole);
//        roleRepository.save(adminRole);
//
//        User user = new User();
//        user.setEmail("admin@gmail.com");
//        user.setPassword(passwordEncoder.encode("admin123")); // Encrypt the password
//        Set<Role> roles = new HashSet<>();
//        roles.add(adminRole);
//        user.setRoles(roles);
//        userRepository.save(user);


        Optional<Role> roleUserOptinal = roleRepository.findByName(Roles.USER);
        if (roleUserOptinal.isEmpty()) {
            Role userRole = Role.builder().name(Roles.USER).build();
            roleRepository.save(userRole);
        }
        Optional<Role> roleRecruiterOptinal = roleRepository.findByName(Roles.RECRUITER);
        if (roleRecruiterOptinal.isEmpty()) {
            Role recruiterRole = Role.builder().name(Roles.RECRUITER).build();
            roleRepository.save(recruiterRole);
        }

        Optional<Role> roleAdminOptinal = roleRepository.findByName(Roles.ADMIN);
        if (roleAdminOptinal.isEmpty()) {
            Role adminRole = Role.builder().name(Roles.ADMIN).build();
            roleRepository.save(adminRole);

            Optional<User> admin = userRepository.findByEmail("admin@gmail.com");
            if (admin.isEmpty()) {
                User user = new User();
                user.setEmail("admin@gmail.com");
                user.setPassword(passwordEncoder.encode("admin123")); // Encrypt the password
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);
                user.setRoles(roles);
                userRepository.save(user);
            }
        }


    }
    }
