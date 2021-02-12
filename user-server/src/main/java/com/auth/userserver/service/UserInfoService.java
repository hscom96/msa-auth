package com.auth.userserver.service;

import com.auth.userserver.model.Users;
import com.auth.userserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserInfoService {
    private final UserRepository userRepository;

    public Page<Users> getUsersInfo(Pageable pageable){
        return userRepository.findAllByEmailVerifiedTrue(pageable);
    }
}
