package com.auth.userserver.service;

import com.auth.userserver.common.constants.ErrorEnum;
import com.auth.userserver.common.exception.CustomException;
import com.auth.userserver.dto.SignUpRequest;
import com.auth.userserver.model.EmailConfirmToken;
import com.auth.userserver.model.Users;
import com.auth.userserver.repository.EmailConfirmTokenRepository;
import com.auth.userserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignUpService {
    private final UserRepository userRepository;
    private final EmailConfirmTokenRepository emailConfirmTokenRepository;
    private final EmailService emailService;
    private final Pbkdf2PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequest signUpRequest){
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Optional<Users> savedUser = userRepository.findByEmail(signUpRequest.getEmail());
        if(savedUser.isPresent()){
            if(savedUser.get().isEmailVerified())
                throw new CustomException(ErrorEnum.USER_ALREADY_EXIST);
            Users users = userRepository.save(Users.of(savedUser.get().getId(), signUpRequest));
            emailService.sendSignUpEmail(users);
            return;
        }

        Users users = userRepository.save(Users.of(signUpRequest));
        emailService.sendSignUpEmail(users);
    }

    public boolean verfiyEmail(UUID confirmTokenId){
        Optional<EmailConfirmToken> confirmToken = emailConfirmTokenRepository.findById(confirmTokenId);
        if(confirmToken.isEmpty()){
            log.info("token is empty [{}]",confirmTokenId);
            return false;
        }
        Users user = userRepository.findByEmail(confirmToken.get().getUsers().getEmail()).get();
        user.setEmailVerified(true);
        userRepository.save(user);
        return true;
    }
}
