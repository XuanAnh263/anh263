package com.example.socialwave.repository;

import com.example.socialwave.entity.Conversation;
import com.example.socialwave.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {


    List<Message> findByConversation(Conversation conversation);
}
