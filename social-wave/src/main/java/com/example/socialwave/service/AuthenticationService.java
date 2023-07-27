package com.example.socialwave.service;

import com.example.socialwave.entity.User;
import com.example.socialwave.repository.UserRepository;
import com.example.socialwave.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
public class AuthenticationService {

    JwtUtils jwtUtils;
    UserRepository userRepository;


    public User getAuthenticatedUser(HttpServletRequest request) {
        String jwt = extractJwtFromRequest(request);

        if (jwt != null) {
            String userEmail = jwtUtils.getUserNameFromJwtToken(jwt);
            return userRepository.findByEmail(userEmail).orElse(null);
        }
        // Nếu không có JWT hoặc không có thông tin người dùng, trả về null hoặc thực hiện xử lý phù hợp.
        return null;
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }




}
