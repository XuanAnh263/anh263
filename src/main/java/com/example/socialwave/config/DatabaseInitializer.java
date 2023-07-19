package com.example.socialwave.config;

import com.example.socialwave.entity.User;
import com.example.socialwave.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatabaseInitializer implements CommandLineRunner {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {


        User user = new User();
        user.setEmail("kttcnh97@gmail.com");
        user.setPassword(passwordEncoder.encode("admin123"));
        user.setUsername("anh");// Encrypt the password
        userRepository.save(user);


//        Optional<Role> roleUserOptinal = roleRepository.findByName(Roles.USER);
//        if (roleUserOptinal.isEmpty()) {
//            Role userRole = Role.builder().name(Roles.USER).build();
//            roleRepository.save(userRole);
//        }
//        Optional<Role> roleRecruiterOptinal = roleRepository.findByName(Roles.RECRUITER);
//        if (roleRecruiterOptinal.isEmpty()) {
//            Role recruiterRole = Role.builder().name(Roles.RECRUITER).build();
//            roleRepository.save(recruiterRole);
//        }
//
//        Optional<Role> roleAdminOptinal = roleRepository.findByName(Roles.ADMIN);
//        if (roleAdminOptinal.isEmpty()) {
//            Role adminRole = Role.builder().name(Roles.ADMIN).build();
//            roleRepository.save(adminRole);
//
//            Optional<User> admin = userRepository.findByEmail("admin@gmail.com");
//            if (admin.isEmpty()) {
//                User user = new User();
//                user.setEmail("admin@gmail.com");
//                user.setPassword(passwordEncoder.encode("admin123")); // Encrypt the password
//                Set<Role> roles = new HashSet<>();
//                roles.add(adminRole);
//                user.setRoles(roles);
//                userRepository.save(user);
//            }
//        }

    }
    }
