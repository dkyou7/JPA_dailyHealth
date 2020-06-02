package com.spo.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
// 다른 패키지에서 생성자 함부로 생성하지 마세요!
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity{

    @Id @Column(name = "user_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    private String name;

    private Float weight;

    private int age;

    private Float height;

    private int cardNum;        // 스포짐 카드 번호

    private Role role;

    @Builder
    public User(Long id, String email, String password, String name,
                Float weight, int age, Float height, int cardNum) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.height = height;
        this.cardNum = cardNum;
        this.role = Role.MEMBER;
    }
}
