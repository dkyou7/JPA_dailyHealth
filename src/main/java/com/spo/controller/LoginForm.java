package com.spo.controller;


import com.spo.domain.Board;
import com.spo.signuplogin.domain.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginForm {
    private Long id;
    private String email;
    private String password;

    public MemberEntity toEntity(){
        MemberEntity memberEntity = MemberEntity.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
        return memberEntity;
    }

    @Builder
    public LoginForm(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
