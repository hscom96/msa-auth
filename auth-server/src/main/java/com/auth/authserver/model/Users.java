package com.auth.authserver.model;

import com.auth.authserver.config.PreventAnyUpdate;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@EntityListeners(PreventAnyUpdate.class)
public class Users {
    @Type(type="uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Id
    private UUID id;

    @Column(length = 20)
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "user_type", nullable = false)
    private int userType;

    @CreationTimestamp
    @Column(name = "create_dt", updatable = false)
    private LocalDateTime createDt;

    @Setter
    @UpdateTimestamp
    @Column(name = "update_dt")
    private LocalDateTime updateDt;
}
