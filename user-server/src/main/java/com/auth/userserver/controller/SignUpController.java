package com.auth.userserver.controller;

import com.auth.userserver.dto.SignUpRequest;
import com.auth.userserver.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/signup")
@RestController
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping
    ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest signUpRequest, Errors error){
        if(error.hasErrors()) {
            String errMsg = Objects.requireNonNull(error.getFieldError()).getDefaultMessage();
            log.info(errMsg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMsg);
        }
        signUpService.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/confirm")
    ResponseEntity<?> verifyEmail(@RequestParam(required = true) UUID confirmToken){
        boolean isVerified = signUpService.verfiyEmail(confirmToken);
        if(!isVerified){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }
}
