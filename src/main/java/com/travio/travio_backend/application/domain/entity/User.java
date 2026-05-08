package com.travio.travio_backend.application.domain.entity;

import com.travio.travio_backend.common.enums.Role;
import com.travio.travio_backend.common.enums.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
public class User extends BaseEntity{
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

}
