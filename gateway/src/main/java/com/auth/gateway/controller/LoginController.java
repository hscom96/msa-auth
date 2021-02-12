package com.auth.gateway.controller;

import com.auth.gateway.dto.LoginRequest;
import com.auth.gateway.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/api/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest loginRequest,
                                       HttpServletResponse httpServletResponse, Errors error){
        if(error.hasErrors()) {
            String errMsg = Objects.requireNonNull(error.getFieldError()).getDefaultMessage();
            log.info(errMsg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMsg);
        }

        String auth = loginService.getJwtByUserInfo(loginRequest);

        return ResponseEntity.ok(auth);
    }

    @PostMapping("/api/refresh")
    public ResponseEntity<?> getJwtByRefreshToken(@RequestParam(required = true) String refreshToken){
        String auth = loginService.getJwtByRefreshToken(refreshToken);
        return ResponseEntity.ok(auth);
    }
}
