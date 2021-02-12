package com.auth.userserver.repository;

import com.auth.userserver.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Page<Users> findAllByEmailVerifiedTrue(Pageable pageable);

    Optional<Users> findByEmail(String email);
}
