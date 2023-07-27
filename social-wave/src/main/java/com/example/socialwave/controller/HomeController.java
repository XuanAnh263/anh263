package com.example.socialwave.controller;


import com.example.socialwave.model.response.ConversationResponse;
import com.example.socialwave.model.response.UserResponse;
import com.example.socialwave.security.CustomUserDetails;
import com.example.socialwave.service.AuthenticationService;
import com.example.socialwave.service.ConversationService;
import com.example.socialwave.service.FriendService;
import com.example.socialwave.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HomeController {

    FriendService friendService;
    AuthenticationService authenticationService;
    UserService userService;
    ConversationService conversationService;
//    @GetMapping("/")
//    public String getAll() {
//        return "index";
//    }
//    @GetMapping("/conversations")
//    public String getAll(Model model) {

//        List<ConversationResponse> conversationResponses = conversationService.getAllConversation();
//        model.addAttribute("conversationList", conversationResponses);
//        return "conversations";
//    }

    @GetMapping("/signin")
    public String getsignin() {

        return "signin";
    }

    @GetMapping("/signup")
    public String getLogout() {

        return "signup";
    }
    @GetMapping("/password-reset")
    public String getResetPass() {

        return "password-reset";
    }
    @GetMapping("/chat-1")
    public String getChatOne() {

        return "chat-1";
    }
    @GetMapping("/chat-2")
    public String getChatTwo() {

        return "chat-2";
    }

    @GetMapping("/settings")
    public String getSetting() {

        return "settings";
    }

    @GetMapping("/create-chat")
    public String getCreateChat() {
        return "create-chat";
    }

//    @GetMapping("/api/v1/friends")
//    public String showFriendList(Model model) {
//        List<UserResponse> friends = friendService.getAllFriends();
//
//        model.addAttribute("friendList",friends );
//        return "friend";
//    }


    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/conversations")
    public String getConversation() {

        return "conversations";
    }
    @GetMapping("/friends")
    public String getFriends() {
        return "friend";
    }


}
