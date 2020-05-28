package com.spo.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserForm {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private Float weight;

    private int age;

    private Float height;

    private int cardNum;        // 스포짐 카드 번호

}
