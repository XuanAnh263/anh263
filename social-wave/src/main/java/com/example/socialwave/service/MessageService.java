package com.example.socialwave.service;

import com.example.socialwave.entity.Conversation;
import com.example.socialwave.entity.Message;
import com.example.socialwave.entity.User;
import com.example.socialwave.exception.InvalidException;
import com.example.socialwave.exception.NotFoundException;
import com.example.socialwave.model.request.ConversationRequest;
import com.example.socialwave.model.request.MessageRequest;
import com.example.socialwave.model.response.MessageResponse;
import com.example.socialwave.repository.ConversationRepository;
import com.example.socialwave.repository.MessageRepository;
import com.example.socialwave.security.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageService {
    MessageRepository messageRepository;
    ObjectMapper objectMapper;
    ConversationRepository conversationRepository;


    public List<MessageResponse> getMessagesInConversation(Long conversationId, CustomUserDetails authenticatedUser) {

        if (authenticatedUser == null) {
            throw new AccessDeniedException("User must be logged in to access this resource.");
        }

        Conversation conversation = getConversationById(conversationId);
        if (!isUserParticipant(conversation, authenticatedUser.getUser())) {
            throw new AccessDeniedException("You are not a member of this conversation.");
        }



        List<Message> messages = messageRepository.findByConversation(conversation);
        return messages.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private boolean isUserParticipant(Conversation conversation, User user) {

        Long ownerId = conversation.getOwnerId();

        Long authenticatedUserId = user.getId();

        return authenticatedUserId.equals(ownerId) || conversation.getUsers().contains(user);
    }

    private MessageResponse convertToResponse(Message message) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setId(message.getId());
        messageResponse.setContentText(message.getContentText());
        messageResponse.setContentRickText(message.getContentRickText());
        messageResponse.setSender(message.getSender());
        messageResponse.setReplyToMessageId(message.getReplyToMessageId());
        messageResponse.setConversation(message.getConversation());

        return messageResponse;
    }


    private Conversation getConversationById(Long conversationId) {

        return conversationRepository.findById(conversationId)
                .orElseThrow(() -> new NotFoundException("Conversation not found"));
    }

    public MessageResponse createMessage(MessageRequest messageRequest, CustomUserDetails authenticatedUser) {
        try {
            Message message = new Message();
            Long conversationId = messageRequest.getConversation().getId();
            Conversation conversation = getConversationById(conversationId);

            message.setConversation(conversation);
            message.setContentText(messageRequest.getContentText());
            message.setContentRickText(messageRequest.getContentRickText());
            message.setReplyToMessageId(messageRequest.getReplyToMessageId());
            message.setMentioned(messageRequest.getMentioned());
            message.setSender(authenticatedUser.getUser());
            message.setCreatDateTime(LocalDateTime.now());
            message.setLastModifiedDateTime(LocalDateTime.now());

            messageRepository.save(message);

            return objectMapper.convertValue(message, MessageResponse.class);
        } catch (InvalidException e) {
            throw new InvalidException("Invalid file data.");
        } catch (NotFoundException e) {
            throw new NotFoundException("Conversation not found");
        }
    }


    public MessageResponse getMessageById(Long id, CustomUserDetails authenticatedUser) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new NotFoundException("Message not found with ID:" + id);
        }
        Message message = messageOptional.get();


        if (authenticatedUser == null) {
            throw new AccessDeniedException("User must be logged in to access this message.");
        }


        if (!message.getSender().equals(authenticatedUser.getUser())) {
            throw new AccessDeniedException("User does not have permission to access this message.");
        }

        return objectMapper.convertValue(message, MessageResponse.class);
    }

    public MessageResponse updateMessage(Long id, String content, CustomUserDetails authenticatedUser) {


        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new NotFoundException("Message not found with ID: " + id);
        }


        Message message = messageOptional.get();
        if (!message.getSender().equals(authenticatedUser.getUser())) {
            throw new AccessDeniedException("User does not have permission to update this message.");
        }

        message.setContentText(content);
        message.setLastModifiedDateTime(LocalDateTime.now());

        messageRepository.save(message);


        return objectMapper.convertValue(message, MessageResponse.class);
    }

    public void deleteMessage(Long id, CustomUserDetails authenticatedUser) {
        if (authenticatedUser == null) {
            throw new AccessDeniedException("User must be logged in to delete a message.");
        }

        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new NotFoundException("Message not found with ID: " + id);
        }

        Message message = messageOptional.get();


        // Kiểm tra xem người dùng có quyền xóa tin nhắn này hay không
        if (!message.getSender().equals(authenticatedUser.getUser())) {
            throw new AccessDeniedException("User does not have permission to delete this message.");
        }

        messageRepository.delete(message);
    }

    public void undeleteMessage(Long id, CustomUserDetails authenticatedUser) {
        if (authenticatedUser == null) {
            throw new AccessDeniedException("User must be logged in to undelete a message.");
        }

        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new NotFoundException("Message not found with ID: " + id);
        }

        Message message = messageOptional.get();

        // Kiểm tra xem người dùng có quyền thu hồi tin nhắn này hay không
        if (!message.getSender().equals(authenticatedUser.getUser())) {
            throw new AccessDeniedException("User does not have permission to undelete this message.");
        }

        message.setDeleted(false); // Thu hồi tin nhắn
        messageRepository.save(message);
    }
}
