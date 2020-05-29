package com.spo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private Float weight;

    private int age;

    private Float height;

    private int cardNum;        // 스포짐 카드 번호

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;  // 유저가 어떤 권한을 가지고 있는지
}
