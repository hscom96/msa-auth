package com.auth.userserver.service;

import com.auth.userserver.common.constants.ErrorEnum;
import com.auth.userserver.common.exception.CustomException;
import com.auth.userserver.common.util.URLCombiner;
import com.auth.userserver.model.EmailConfirmToken;
import com.auth.userserver.model.Users;
import com.auth.userserver.repository.EmailConfirmTokenRepository;
import com.auth.userserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final URLCombiner urlCombiner;
    private final EmailConfirmTokenRepository emailConfirmTokenRepository;
    private final UserRepository userRepository;
    @Value("${spring.mail.username}")
    private String senderEmail;

    public void sendSignUpEmail(Users users) {
        Optional<Users> savedUser = userRepository.findByEmail(users.getEmail());
        if(savedUser.isEmpty())
            throw new CustomException(ErrorEnum.USER_NOT_FOUND);
        if(savedUser.get().isEmailVerified())
            throw new CustomException(ErrorEnum.USER_ALREADY_CONFIRMED);

        EmailConfirmToken emailConfirmToken = EmailConfirmToken.builder()
                .users(users).build();
        emailConfirmTokenRepository.save(emailConfirmToken);

        String path = UriComponentsBuilder.fromPath("/api/signup/confirm")
                .queryParam("confirmToken", emailConfirmToken.getId()).toUriString();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailConfirmToken.getUsers().getEmail());
        message.setFrom(senderEmail);
        message.setSubject("[소울메이츠] 회원 가입 인증 이메일");
        message.setText("회원 인증 주소: " + urlCombiner.combinePathWithHost(path));

        mailSender.send(message);
    }
}
