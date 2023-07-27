package com.example.socialwave.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfigInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            modelAndView.addObject("user_fullname", principal.getUsername());
//            modelAndView.addObject("user_phone", principal.get.getPhone());
//            modelAndView.addObject("user_email", principal.getUser().getEmail());
//            modelAndView.addObject("user_address", principal.getUser().getAddress());
            modelAndView.addObject("isLogined", true);
        } else {
            modelAndView.addObject("isLogined", false);
        }
    }
}
