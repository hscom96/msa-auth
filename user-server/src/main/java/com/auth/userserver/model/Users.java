package com.auth.userserver.model;

import com.auth.userserver.dto.SignUpRequest;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Users {
    @Type(type="uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Id
    private UUID id;

    @Column(name = "name",length = 30)
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @Column(name = "user_type", nullable = false)
    private int userType;

    @CreationTimestamp
    @Column(name = "create_dt", updatable = false)
    private LocalDateTime createDt;

    @Setter
    @UpdateTimestamp
    @Column(name = "update_dt")
    private LocalDateTime updateDt;

    public static Users of(SignUpRequest signUpRequest){
        return Users.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .emailVerified(false)
                .userType(1).build();
    }

    public static Users of(UUID id, SignUpRequest signUpRequest){
        return Users.builder()
                .id(id)
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .emailVerified(false)
                .userType(1).build();
    }

    public boolean isEmailVerified(){
        return emailVerified;
    }
}
