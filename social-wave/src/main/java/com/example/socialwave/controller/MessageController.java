package com.example.socialwave.controller;

import com.example.socialwave.entity.Conversation;
import com.example.socialwave.exception.InvalidException;
import com.example.socialwave.exception.NotFoundException;
import com.example.socialwave.model.request.MessageRequest;
import com.example.socialwave.model.response.MessageResponse;
import com.example.socialwave.security.CustomUserDetails;
import com.example.socialwave.security.CustomUserDetailsService;
import com.example.socialwave.service.MessageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageController {
    MessageService messageService;
    CustomUserDetailsService customUserDetails;
//    @PostMapping
//    public ResponseEntity<MessageResponse> createMessage(@RequestBody MessageRequest messageRequest) {
//        CustomUserDetails authenticatedUser = getAuthenticatedUserFromSecurityContextHolder();
//
//            MessageResponse messageResponse = messageService.createMessage(messageRequest, authenticatedUser);
//            return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);
//
//    }
//    private CustomUserDetails getAuthenticatedUserFromSecurityContextHolder() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof CustomUserDetails) {
//            return (CustomUserDetails) authentication.getPrincipal();
//        }
//        return null;
//    }

    @GetMapping("/{conversationId}")
    public ResponseEntity<?> getMessagesInConversation(@PathVariable Long conversationId, Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in to create a message.");
        }

        String authenticatedUsername = principal.getName();
        CustomUserDetails authenticatedUser = (CustomUserDetails) customUserDetails.loadUserByUsername(authenticatedUsername);
        List<MessageResponse> messages = messageService.getMessagesInConversation(conversationId, authenticatedUser);
        return ResponseEntity.ok(messages);
    }


    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody MessageRequest messageRequest, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in to create a message.");
        }

        String authenticatedUsername = principal.getName();
        CustomUserDetails authenticatedUser = (CustomUserDetails) customUserDetails.loadUserByUsername(authenticatedUsername);

        try {
            MessageResponse messageResponse = messageService.createMessage(messageRequest, authenticatedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);
        } catch (InvalidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<?> getMessageById(@PathVariable Long id, Principal principal) {
        String authenticatedUsername = principal.getName();
        CustomUserDetails authenticatedUser = (CustomUserDetails) customUserDetails.loadUserByUsername(authenticatedUsername);
        try {
            MessageResponse messageResponse = messageService.getMessageById(id, authenticatedUser);
            return ResponseEntity.ok(messageResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable Long id, @RequestParam String content, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be logged in to create a message.");
        }
        String authenticatedUsername = principal.getName();
        CustomUserDetails authenticatedUser = (CustomUserDetails) customUserDetails.loadUserByUsername(authenticatedUsername);

        try {
            MessageResponse messageResponse = messageService.updateMessage(id, content, authenticatedUser);
            return ResponseEntity.ok(messageResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id, Principal principal) {
        String authenticatedUsername = principal.getName();
        CustomUserDetails authenticatedUser = (CustomUserDetails) customUserDetails.loadUserByUsername(authenticatedUsername);

        try {
            messageService.deleteMessage(id, authenticatedUser);
            return ResponseEntity.ok("Message deleted successfully.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/undelete")
    public ResponseEntity<String> undeleteMessage(@PathVariable Long id, Principal principal) {
        String authenticatedUsername = principal.getName();
        CustomUserDetails authenticatedUser = (CustomUserDetails) customUserDetails.loadUserByUsername(authenticatedUsername);

        try {
            messageService.undeleteMessage(id, authenticatedUser);
            return ResponseEntity.ok("Message undeleted successfully.");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    private CustomUserDetails getAuthenticatedUserFromSecurityContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            // Ép kiểu principal sang CustomUserDetails nếu đúng kiểu
            return (CustomUserDetails) authentication.getPrincipal();
        }

        return null;
    }

//    private CustomUserDetails getAuthenticatedUser(Principal principal) {
//        if (principal == null) {
//            throw new AccessDeniedException("User must be logged in to perform this action.");
//        }
//        String authenticatedUsername = principal.getName();
//        return (CustomUserDetails) customUserDetails.loadUserByUsername(authenticatedUsername);
//    }

//    @PostMapping
//    public ResponseEntity<?> createMessage(@RequestBody MessageRequest messageRequest, Principal principal) {
//        CustomUserDetails authenticatedUser = getAuthenticatedUser(principal);
//
//        try {
//            MessageResponse messageResponse = messageService.createMessage(messageRequest, authenticatedUser);
//            return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);
//        } catch (InvalidException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getMessageById(@PathVariable Long id, Principal principal) {
//        CustomUserDetails authenticatedUser = getAuthenticatedUser(principal);
//        try {
//            MessageResponse messageResponse = messageService.getMessageById(id, authenticatedUser);
//            return ResponseEntity.ok(messageResponse);
//        } catch (NotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateMessage(@PathVariable Long id, @RequestParam String content, Principal principal) {
//        CustomUserDetails authenticatedUser = getAuthenticatedUser(principal);
//
//        try {
//            MessageResponse messageResponse = messageService.updateMessage(id, content, authenticatedUser);
//            return ResponseEntity.ok(messageResponse);
//        } catch (NotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (AccessDeniedException e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteMessage(@PathVariable Long id, Principal principal) {
//        CustomUserDetails authenticatedUser = getAuthenticatedUser(principal);
//
//        try {
//            messageService.deleteMessage(id, authenticatedUser);
//            return ResponseEntity.ok("Message deleted successfully.");
//        } catch (NotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (AccessDeniedException e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
//        }
//    }
//
//    @PatchMapping("/{id}/undelete")
//    public ResponseEntity<String> undeleteMessage(@PathVariable Long id, Principal principal) {
//        CustomUserDetails authenticatedUser = getAuthenticatedUser(principal);
//
//        try {
//            messageService.undeleteMessage(id, authenticatedUser);
//            return ResponseEntity.ok("Message undeleted successfully.");
//        } catch (NotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (AccessDeniedException e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
//        }
//    }
}
