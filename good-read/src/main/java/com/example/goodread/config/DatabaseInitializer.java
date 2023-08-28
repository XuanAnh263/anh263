package com.example.goodread.config;

import com.example.goodread.entity.Role;
import com.example.goodread.entity.User;
import com.example.goodread.repository.RoleRepository;
import com.example.goodread.repository.UserRepository;
import com.example.goodread.statics.Roles;
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
        Optional<Role> roleUserOptional = roleRepository.findByName(Roles.USER);
        if (roleUserOptional.isEmpty()) {
            Role userRole = Role.builder().name(Roles.USER).build();
            roleRepository.save(userRole);
        }

        Optional<Role> roleAuthorOptional = roleRepository.findByName(Roles.AUTHOR);
        if (roleAuthorOptional.isEmpty()) {
            Role authorRole = Role.builder().name(Roles.AUTHOR).build();
            roleRepository.save(authorRole);
        }

        Optional<Role> roleAdminOptional = roleRepository.findByName(Roles.ADMIN);
        if (roleAdminOptional.isEmpty()) {
            Role adminRole = Role.builder().name(Roles.ADMIN).build();
            roleRepository.save(adminRole);

            Optional<User> admin = userRepository.findByEmail("admin@gmail.com");
            if (admin.isEmpty()) {
                User user = new User();
                user.setEmail("admin@gmail.com");
                user.setActivated(true);
                user.setAvatar("https://firebasestorage.googleapis.com/v0/b/fir-e9a96.appspot.com/o/images%2Fu_60x60-267f0ca0ea48fd3acfd44b95afa64f01.png?alt=media&token=894f32ca-266a-40c1-81c0-eb7f8142f13a");
                user.setPhone("");
                user.setFullName("");
                user.setPassword(passwordEncoder.encode("admin123")); // Encrypt the password
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);
                user.setRoles(roles);
                userRepository.save(user);
            }
        }
    }

}
