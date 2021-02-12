package com.auth.userserver.controller;

import com.auth.userserver.dto.PageLimitRequest;
import com.auth.userserver.dto.UserInfoResponse;
import com.auth.userserver.model.Users;
import com.auth.userserver.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserInfoService userInfoService;

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<?> getUsersInfo(@Valid PageLimitRequest pageLimitRequest, Errors error){
        if(error.hasErrors()) {
            String errMsg = Objects.requireNonNull(error.getFieldError()).getDefaultMessage();
            log.info(errMsg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMsg);
        }

        Page<Users> userList = userInfoService.getUsersInfo(pageLimitRequest.of());
        Page<UserInfoResponse> userInfoResponses = userList.map(UserInfoResponse::of);
        return ResponseEntity.ok(userInfoResponses);
    }
}
