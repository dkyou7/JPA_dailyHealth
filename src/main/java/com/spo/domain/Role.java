package com.spo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("관리자"),
    LEADER("리더"),
    MEMBER("회원");

    private String value;
}
