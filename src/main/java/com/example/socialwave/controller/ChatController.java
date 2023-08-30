package com.example.socialwave.controller;

import com.example.socialwave.entity.ChatMessage;
import com.example.socialwave.entity.ChatNotification;
import com.example.socialwave.model.dto.ChatMessageDTO;
import com.example.socialwave.model.mapper.ChatMessageMapper;
import com.example.socialwave.service.impl.ChatMessageService;
import com.example.socialwave.service.impl.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ChatController {
    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {


        ChatMessageDTO chatMessageDTO = ChatMessageMapper.toChatMessageDTO(chatMessage);
        int senderId = chatMessageDTO.getSenderId();
        int recipientId =chatMessageDTO.getRecipientId();
        var chatId = chatRoomService
                .getChatId(senderId, recipientId, true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(recipientId),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId().getId(),
                        saved.getSenderName()));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable int senderId,
            @PathVariable int recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable int senderId,
                                                @PathVariable int recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable int id) {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }
}
