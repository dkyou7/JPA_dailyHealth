package com.spo.controller;

import com.spo.domain.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserForm {

    private Long id;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private Float weight;

    private int age;

    private Float height;

    private int cardNum;        // 스포짐 카드 번호

    @Builder
    public UserForm(Long id, String email, String password, String name,
                    Float weight, int age, Float height, int cardNum) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.height = height;
        this.cardNum = cardNum;
    }

    public User toEntity(){
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .weight(weight)
                .height(height)
                .age(age)
                .cardNum(cardNum)
                .build();
    }

}
