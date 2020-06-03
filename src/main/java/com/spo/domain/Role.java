package com.spo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    LEADER("ROLE_LEADER"),
    MEMBER("ROLE_MEMBER");

    private String value;
}
