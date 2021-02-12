package com.auth.authserver.service;

import com.auth.authserver.model.CustomUserDetail;
import com.auth.authserver.model.Users;
import com.auth.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository UserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = UserRepository.findByEmail(username);
        if (user.isEmpty() || !user.get().isEmailVerified()) {
            throw new UsernameNotFoundException("wrongId or not emailVerfied");
        }
        return CustomUserDetail.of(user.get());
    }
}