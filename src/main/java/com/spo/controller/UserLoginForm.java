package com.spo.controller;

import com.spo.domain.BaseTimeEntity;
import com.spo.domain.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserLoginForm extends BaseTimeEntity {
    private Long id;
    private String email;
    private String password;

    public User toEntity(){
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public UserLoginForm(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}