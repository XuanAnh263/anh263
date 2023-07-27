package com.example.socialwave.service;

import com.example.socialwave.entity.Message;
import com.example.socialwave.entity.User;
import com.example.socialwave.exception.InvalidException;
import com.example.socialwave.exception.NotFoundException;
import com.example.socialwave.model.request.MessageRequest;
import com.example.socialwave.model.response.MessageResponse;
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageService {
    MessageRepository messageRepository;
    ObjectMapper objectMapper;

    public MessageResponse createMessage(MessageRequest messageRequest, CustomUserDetails authenticatedUser) {
        try {
            Message message = new Message();
            message.setConversationId(messageRequest.getConversationId());
            message.setContentText(messageRequest.getContentText());
            message.setContentRickText(messageRequest.getContentRickText());
            message.setReplyToMessageId(messageRequest.getReplyToMessageId());
            message.setMentioned(messageRequest.getMentioned());
            message.setSenderId(authenticatedUser.getUser());
            message.setCreatDateTime(LocalDateTime.now());
            message.setLastModifiedDateTime(LocalDateTime.now());

            messageRepository.save(message);

            return objectMapper.convertValue(message, MessageResponse.class);
        } catch (InvalidException e) {
            throw new InvalidException("Invalid file data.");
        }
    }


    public MessageResponse getMessageById(Long id, CustomUserDetails authenticatedUser) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new NotFoundException("Message not found with ID:" + id);
        }
        Message message = messageOptional.get();

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        if (authenticatedUser == null) {
            throw new AccessDeniedException("User must be logged in to access this message.");
        }

//        // Kiểm tra xem người dùng có quyền truy cập tin nhắn này hay không
//        if (!message.getSenderId().equals(authenticatedUser.getUser())) {
//            throw new AccessDeniedException("User does not have permission to access this message.");
//        }

        return objectMapper.convertValue(messageOptional, MessageResponse.class);
    }

    public MessageResponse updateMessage(Long id, String content) {
        // Xử lý cập nhật nội dung của message theo id ở đây.
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new NotFoundException("Message not found with ID: " + id);
        }


        Message message = messageOptional.get();
        message.setContentText(content);
        message.setLastModifiedDateTime(LocalDateTime.now());

        // Lưu message sau khi cập nhật vào cơ sở dữ liệu hoặc hệ thống lưu trữ khác.
        messageRepository.save(message);


        return objectMapper.convertValue(message, MessageResponse.class);
    }

    public void deleteMessage(Long id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isEmpty()) {
            throw new NotFoundException("Message not found with ID: " + id);
        }

        Message message = messageOptional.get();

        messageRepository.delete(message);
    }
}
