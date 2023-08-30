package com.example.socialwave.model.mapper;

import com.example.socialwave.entity.ChatMessage;
import com.example.socialwave.model.dto.ChatMessageDTO;

public class ChatMessageMapper {
    public static ChatMessageDTO toChatMessageDTO(ChatMessage chatMessage) {
        ChatMessageDTO tmp = new ChatMessageDTO();
        tmp.setId(chatMessage.getId());
        tmp.setSenderId(chatMessage.getSenderId().getId());
        tmp.setRecipientId(chatMessage.getRecipientId().getId());
        return tmp;
    }
}
