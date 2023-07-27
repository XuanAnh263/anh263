package com.example.socialwave.service;

import com.example.socialwave.entity.Conversation;
import com.example.socialwave.entity.User;
import com.example.socialwave.exception.NotFoundException;
import com.example.socialwave.model.request.ConversationRequest;
import com.example.socialwave.model.response.ConversationResponse;
import com.example.socialwave.repository.ConversationRepository;
import com.example.socialwave.repository.UserRepository;
import com.example.socialwave.security.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConversationService {
    ConversationRepository conversationRepository;
    UserRepository userRepository;
    AuthenticationService authenticationService;
    ObjectMapper objectMapper;

    public List<ConversationResponse> getAllConversation() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails authenticatedUser) {
            // Ép kiểu principal sang CustomUserDetails nếu đúng kiểu

            // Lấy thông tin cần thiết từ CustomUserDetails
            Long userId = authenticatedUser.getId();

            // Gọi repository hoặc service để lấy danh sách Conversation dựa trên userId
//            List<Conversation> conversations = conversationRepository.findByOwnerId(userId);
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



    public ConversationResponse addConversation(ConversationRequest conversationRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            // Ép kiểu principal sang CustomUserDetails nếu đúng kiểu
            CustomUserDetails authenticatedUser = (CustomUserDetails) authentication.getPrincipal();

            // Lấy thông tin cần thiết từ CustomUserDetails
            Long userId = authenticatedUser.getId();

            Conversation conversation = new Conversation();
            copyConversationRequestToConversation(conversationRequest, conversation);
            conversation.setOwnerId(userId);

            Conversation newConversation = conversationRepository.save(conversation);

            return convertToConversationResponse(newConversation);
        }
        return null;

//        Conversation conversation = new Conversation();
//        copyConversationRequestToConversation(conversationRequest, conversation);
//        conversation.setOwnerId(authenticatedUser.getId());
//
//        Conversation newConversation = conversationRepository.save(conversation);
//        return convertToConversationResponse(newConversation);
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
