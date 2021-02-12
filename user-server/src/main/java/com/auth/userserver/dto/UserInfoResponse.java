package com.auth.userserver.dto;

import com.auth.userserver.model.Users;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class UserInfoResponse {
    private UUID id;

    private String name;

    private String email;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

    public static UserInfoResponse of(Users users){
        return UserInfoResponse.builder()
                .createDt(users.getCreateDt())
                .name(users.getName())
                .email(users.getEmail())
                .updateDt(users.getUpdateDt())
                .id(users.getId()).build();
    }
}
