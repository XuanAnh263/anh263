package com.example.socialwave.controller;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody MessageRequest messageRequest, Principal principal) {
        if (principal == null) {
            // Nếu principal là null, tức là người dùng chưa đăng nhập
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

    @GetMapping("/{id}")
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
}
