package com.example.socialwave.security;

import com.example.socialwave.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return user.getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
//                .collect(Collectors.toList());
//    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return user.getUsername();
    }

    public LocalDate getDob() {
        return user.getDob();
    }

    public String getGender() {
        return user.getGender();
    }


    public String getAvatar() {
        return user.getAvatar();
    }

    public String getAddress() {
        return user.getAddress();
    }

    public String getPhone() {
        return user.getPhone();
    }

    public User getUser() {
        return user;
    }
}
