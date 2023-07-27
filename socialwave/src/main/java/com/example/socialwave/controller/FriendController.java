package com.example.socialwave.controller;

import com.example.socialwave.entity.User;
import com.example.socialwave.exception.NotFoundException;
import com.example.socialwave.model.request.FriendRequest;
import com.example.socialwave.model.request.UserRequest;
import com.example.socialwave.security.CustomUserDetails;
import com.example.socialwave.service.AuthenticationService;
import com.example.socialwave.service.FriendService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/friends")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendController {

    FriendService friendService;
    AuthenticationService authenticationService;
    private CustomUserDetails getAuthenticatedUserFromSecurityContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof CustomUserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }
        return null;
    }
    @PostMapping("/request/{user_id}")
    public ResponseEntity<?> sendFriendRequest(HttpServletRequest request, @PathVariable("user_id") Long friendId) {
        try {
            User authenticationUser = getAuthenticatedUser(request);
            UserRequest userRequest = new UserRequest();
            userRequest.setUserId(authenticationUser.getId());

            FriendRequest friendRequest = new FriendRequest();
            friendRequest.setFriendId(friendId);

            friendService.sendFriendRequest(userRequest, friendRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/request/{request_id}/accept")
    public ResponseEntity<?> acceptFriendRequest(HttpServletRequest request,
                                                 @PathVariable("request_id") Long requestId,
                                                 @RequestBody UserRequest userRequest) {

        try {
            User authenticationUser = getAuthenticatedUser(request);

            //kiểm tra xem ng dùng đã xác thực có quyền chấp nhận lời mời ko
            if (!authenticationUser.getId().equals(userRequest.getUserId())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            friendService.acceptFriendRequest(requestId, userRequest.getUserId());
            return ResponseEntity.ok().build();
        } catch (NotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/request/{request_id}/denial")
    public ResponseEntity<?> denyFriendRequest(HttpServletRequest request,
                                               @PathVariable("request_id") Long requestId,
                                               @RequestBody UserRequest userRequest) {
        try {
            User authenticationUser = getAuthenticatedUser(request);

            //kiểm tra xem ng dùng đã xác thực có quyền chấp nhận lời mời ko
            if (!authenticationUser.getId().equals(userRequest.getUserId())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            friendService.denyFriendRequest(requestId, userRequest.getUserId());
            return ResponseEntity.ok().build();
        } catch (NotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFriend(HttpServletRequest request, @PathVariable Long id) {
        try {
            User authenticatedUser = getAuthenticatedUser(request);
            friendService.deleteFriend(id, authenticatedUser);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    public User getAuthenticatedUser(HttpServletRequest request) {
        return authenticationService.getAuthenticatedUser(request);
    }


}
