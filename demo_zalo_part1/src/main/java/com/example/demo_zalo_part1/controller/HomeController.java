package com.example.demo_zalo_part1.controller;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    @GetMapping("/")
    public String getAll() {

        return "signin";
    }
    @GetMapping("/index")
    public String getIndex() {

        return "index";
    }

    @GetMapping("/signin")
    public String getLogin() {

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

    @GetMapping("/friend")
    public String getFriend() {
        return "friend";
    }


    @GetMapping("/profile")
    public String getProfile() {
        return "profile";
    }



}
