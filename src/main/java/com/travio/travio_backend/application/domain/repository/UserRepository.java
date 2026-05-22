package com.travio.travio_backend.application.domain.repository;

import com.travio.travio_backend.application.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
     Optional<User> findByEmail(String email);
}
