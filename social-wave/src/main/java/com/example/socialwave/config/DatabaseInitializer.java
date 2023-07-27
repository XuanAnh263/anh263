package com.example.socialwave.config;

import com.example.socialwave.entity.*;
import com.example.socialwave.repository.ConversationRepository;
import com.example.socialwave.repository.FriendRepository;
import com.example.socialwave.repository.MessageRepository;
import com.example.socialwave.repository.UserRepository;
import com.example.socialwave.statics.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatabaseInitializer implements CommandLineRunner {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;
    FriendRepository friendRepository;
    ConversationRepository conversationRepository;
    MessageRepository messageRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {


//        User user = new User();
//        user.setEmail("kttcnh97@gmail.com");
//        user.setPassword(passwordEncoder.encode("admin123"));
//        user.setUsername("anh");// Encrypt the password
//        userRepository.save(user);

        List<User> usersToAdd = List.of(
                new User("kttcnh97@gmail.com", passwordEncoder.encode("admin123"), "anh", 1L, true),
                new User("admin1@gmail.com", passwordEncoder.encode("user123"), "xuan anh",2L,true),
                new User("admin2@gmail.com", passwordEncoder.encode("user456"), "user2",3L,true),
                new User("admin3@gmail.com", passwordEncoder.encode("user4567"), "user3",4L,true),
                new User("admin4@gmail.com", passwordEncoder.encode("user45678"), "user4",5L,true)

        );

        userRepository.saveAll(usersToAdd);
        // Lấy người dùng từ cơ sở dữ liệu bằng email
        Optional<User> optionalUser1 = userRepository.findByEmail("kttcnh97@gmail.com");
        Optional<User> optionalUser2 = userRepository.findByEmail("admin1@gmail.com");
        Optional<User> optionalUser3 = userRepository.findByEmail("admin2@gmail.com");
        Optional<User> optionalUser4 = userRepository.findByEmail("admin3@gmail.com");
        Optional<User> optionalUse5 = userRepository.findByEmail("admin4@gmail.com");

        // Kiểm tra và lấy đối tượng User từ Optional (nếu tồn tại)
        User user1 = optionalUser1.orElseThrow(() -> new RuntimeException("User not found"));
        User user2 = optionalUser2.orElseThrow(() -> new RuntimeException("User not found"));
        User user3 = optionalUser3.orElseThrow(() -> new RuntimeException("User not found"));
        User user4 = optionalUser4.orElseThrow(() -> new RuntimeException("User not found"));


        // Tạo danh sách bạn
        List<Friend> friendsToAdd = List.of(
                new Friend(user1, user2, FriendStatus.ACCEPT),
                new Friend(user1, user3, FriendStatus.ACCEPTED)
                // Thêm các bạn khác vào danh sách nếu cần
        );

        // Lưu danh sách bạn vào cơ sở dữ liệu
        friendRepository.saveAll(friendsToAdd);



        List<Conversation> conversationsToAdd = List.of(
                new Conversation(1L,"Conversation 1", "avatar_url_1", "Description for Conversation 1", 1L),
                new Conversation(2L,"Conversation 2", "avatar_url_2", "Description for Conversation 2",2L)

        );


        conversationRepository.saveAll(conversationsToAdd);

//

        Optional<Conversation> optionalConversation1 = conversationRepository.findById(1L);
        Optional<Conversation> optionalConversation2 = conversationRepository.findById(2L);

        Conversation conversation1 = optionalConversation1.orElseThrow(() -> new RuntimeException("Conversation not found"));
        Conversation conversation2 = optionalConversation2.orElseThrow(() -> new RuntimeException("Conversation not found"));




        // Create Messages
        List<Message> messagesToAdd = List.of(
                new Message(conversation1, user1,  MessageType.TEXT, "xin chào contentText", "contentRickText", false, false, false, null, MessageStatus.SENT, LocalDateTime.now(), LocalDateTime.now()),
                new Message(conversation1, user2,  MessageType.TEXT, "Hello! contentText", "contentRickText", false, false, false, null, MessageStatus.SENT, LocalDateTime.now(), LocalDateTime.now()),
                new Message(conversation2, user1,  MessageType.TEXT, "hi friend contentText", "contentRickText", false, false, false, null, MessageStatus.SENT, LocalDateTime.now(), LocalDateTime.now())
                // Add more messages here if needed
        );

        // Save Messages to the database
        messageRepository.saveAll(messagesToAdd);

//        conversation1.getUsers().addAll(usersToAdd);
//        conversation2.getUsers().addAll(usersToAdd);
//
//        conversationRepository.save(conversation1);
//        conversationRepository.save(conversation2);



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
