package com.example.socialwave.service;

import com.example.socialwave.entity.Conversation;
import com.example.socialwave.entity.Friend;
import com.example.socialwave.entity.User;
import com.example.socialwave.exception.InvalidRequestException;
import com.example.socialwave.exception.NotFoundException;
import com.example.socialwave.model.request.ConversationRequest;
import com.example.socialwave.model.response.ConversationResponse;
import com.example.socialwave.repository.ConversationRepository;
import com.example.socialwave.repository.FriendRepository;
import com.example.socialwave.repository.UserRepository;
import com.example.socialwave.security.CustomUserDetails;
import com.example.socialwave.statics.ConversationStatus;
import com.example.socialwave.statics.ConversationType;
import com.example.socialwave.statics.FriendStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConversationService {
    ConversationRepository conversationRepository;
    UserRepository userRepository;
    AuthenticationService authenticationService;
    ObjectMapper objectMapper;
    FriendRepository friendRepository;


    public List<ConversationResponse> getAllConversation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails authenticatedUser) {
            // Ép kiểu principal sang CustomUserDetails nếu đúng kiểu

            // Lấy thông tin cần thiết từ CustomUserDetails
            Long userId = authenticatedUser.getId();


            List<Conversation> conversations = conversationRepository.findByOwnerId(userId);

            // Chuyển đổi danh sách Conversation thành danh sách ConversationResponse
            List<ConversationResponse> conversationResponses = conversations.stream()
                    .map(this::convertToConversationResponse)
                    .collect(Collectors.toList());

            return conversationResponses;
        } else {
            return Collections.emptyList();
        }
    }

    public ConversationResponse addConversation(ConversationRequest conversationRequest, ConversationType conversationType, Set<Long> userIds) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            return null;
        }

        CustomUserDetails authenticatedUser = (CustomUserDetails) authentication.getPrincipal();
        Long userId = authenticatedUser.getId();

        Conversation conversation = new Conversation();
        copyConversationRequestToConversation(conversationRequest, conversation);
        conversation.setOwnerId(userId);

        Set<User> users = new HashSet<>();
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        users.add(currentUser);

        if (conversationType == ConversationType.PERSONAL) {

            if (userIds.size() != 2 || !userIds.contains(userId)) {
                throw new IllegalStateException("Cannot create personal conversation, target user must be a friend.");
            }

            users.addAll(userRepository.findAllById(userIds));
        } else if (conversationType == ConversationType.GROUP) {

            List<User> otherUsers = findOtherUsersForGroupConversation(userId, userIds);
            if (otherUsers.size() < 2) {
                throw new IllegalStateException("Cannot create group conversation, at least 3 users required.");
            }


            if (!userIds.contains(userId)) {
                throw new IllegalStateException("Cannot create group conversation, all target users must be friends.");
            }

            users.addAll(otherUsers);
        }
        //TODO chat GROUP đang lỗi

        Conversation newConversation = conversationRepository.save(conversation);
        ConversationResponse conversationResponse = convertToConversationResponse(newConversation);
        conversationResponse.setUserIds(users.stream().map(User::getId).collect(Collectors.toSet()));
        return conversationResponse;
    }




    private User findAnotherUserForPersonalConversation(Long userId, Set<User> existingUsers) {
        List<User> allUsers = userRepository.findAll();
        Set<Long> existingUserIds = existingUsers.stream().map(User::getId).collect(Collectors.toSet());

        for (User user : allUsers) {
            System.out.println("Checking user: " + user.getId());
            System.out.println("User is owner: " + user.getId().equals(userId));
            System.out.println("userId: " + userId);
            System.out.println("User is in existingUsers: " + existingUserIds.contains(user.getId()));

            if (!user.getId().equals(userId) && !existingUserIds.contains(user.getId())) {
                return user;
            }
        }
        return null;
    }


    private List<User> findOtherUsersForGroupConversation(Long userId, Set<Long> userIds) {
        List<User> allUsers = userRepository.findAll();
        List<User> otherUsers = new ArrayList<>();

        int requiredParticipants = 3;

        if (userIds == null || !userIds.contains(userId)) {
            User creator = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
            otherUsers.add(creator);
        }

        int totalParticipants = otherUsers.size() + (userIds != null ? userIds.size() : 0);

        if (totalParticipants < requiredParticipants) {
            throw new IllegalStateException("Cannot create group conversation, at least 3 users required.");
        }

        for (User user : allUsers) {
            if (!user.getId().equals(userId) && (userIds == null || !userIds.contains(user.getId()))) {
                otherUsers.add(user);
            }
        }
//TODO lỗi truyn vào 1,2,4 mà trả về 1,3,5
        return otherUsers;
    }



    public ConversationResponse getConversationById(Long id, CustomUserDetails authenticatedUser) {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conversation not found by " + id));
        return convertToConversationResponse(conversation);
    }


    public ConversationResponse updateConversation(Long id, ConversationRequest request) {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conversation not found by " + id));
        CustomUserDetails authenticatedUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!conversation.getOwnerId().equals(authenticatedUser.getId())) {
            throw new AccessDeniedException("You do not have permission to update this conversation.");
        }

        copyConversationRequestToConversation(request, conversation);

        if (request.getUserIds() != null) {
            conversation.setUsers(getUsersFromUserIds(request.getUserIds()));
        }

        Conversation updatedConversation = conversationRepository.save(conversation);
        return convertToConversationResponse(updatedConversation);
    }

    public void deleteConversation(Long id) {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conversation not found by " + id));

        CustomUserDetails authenticatedUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!conversation.getOwnerId().equals(authenticatedUser.getId())) {
            throw new AccessDeniedException("You do not have permission to delete this conversation.");
        }


        conversation.getUsers().forEach(user -> user.getConversations().remove(conversation));


        conversationRepository.delete(conversation);
    }

    private ConversationResponse convertToConversationResponse(Conversation conversation) {
        return objectMapper.convertValue(conversation, ConversationResponse.class);
    }

    private void copyConversationRequestToConversation(ConversationRequest conversationRequest, Conversation conversation) {
        conversation.setName(conversationRequest.getName());
        conversation.setAvatar(conversationRequest.getAvatar());
        conversation.setDescription(conversationRequest.getDescription());
        conversation.setType(conversationRequest.getType());
        conversation.setStatus(conversationRequest.getStatus());
        conversation.setCreatDateTime(LocalDateTime.now());
        conversation.setLastModifiedDateTime(LocalDateTime.now());
    }

    private Set<User> getUsersFromUserIds(Set<Long> userIds) {
        return userIds.stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("User not found by id: " + id)))
                .collect(Collectors.toSet());
    }


}
