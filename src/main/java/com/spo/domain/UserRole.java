package com.spo.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;


public class UserRole implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_id;

    @Column(name = "user_role", columnDefinition = "enum('MEMBER','ADMIN','LEADER')")
    @Enumerated(EnumType.STRING)
    private Role role = Role.MEMBER;

    public UserRole(String id, Role role) {
        this.user_id = id;
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.getAuthority().toString();
    }
}
