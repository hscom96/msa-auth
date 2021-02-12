package com.auth.authserver.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class CustomUserDetail implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

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

    public static CustomUserDetail of(Users user) {
        List<GrantedAuthority> Authoritylist = new ArrayList<>();
        switch (user.getUserType()) {
            case 0:
                Authoritylist.add(new SimpleGrantedAuthority("ADMIN"));
            case 1:
                Authoritylist.add(new SimpleGrantedAuthority("USER"));
                break;
        }

        return CustomUserDetail.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(Authoritylist).build();
    }
}