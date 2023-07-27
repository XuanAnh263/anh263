package com.example.socialwave.controller;

import com.example.socialwave.model.request.ConversationRequest;
import com.example.socialwave.model.response.ConversationResponse;
import com.example.socialwave.security.CustomUserDetails;
import com.example.socialwave.service.ConversationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/conversations")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConversationController {
    ConversationService conversationService;



    @PostMapping
    public ResponseEntity<ConversationResponse> addConversation(@RequestBody ConversationRequest conversationRequest) {


        ConversationResponse newConversation = conversationService.addConversation(conversationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newConversation);
    }
    private CustomUserDetails getAuthenticatedUserFromSecurityContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            // Ép kiểu principal sang CustomUserDetails nếu đúng kiểu
            return (CustomUserDetails) authentication.getPrincipal();
        }

        return null;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ConversationResponse> getConversationById(@PathVariable Long id) {
        CustomUserDetails authenticatedUser = getAuthenticatedUserFromSecurityContextHolder();
        ConversationResponse response = conversationService.getConversationById(id, authenticatedUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversationResponse> updateConversation(@PathVariable Long id, @RequestBody ConversationRequest conversationRequest) {
        ConversationResponse response = conversationService.updateConversation(id, conversationRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConversation(@PathVariable Long id) {
        conversationService.deleteConversation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
