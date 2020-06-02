package com.spo.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 파라메터가 없는 기본 생성자를 만들어준다.
@Getter @Setter
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 다측은 아이디랑 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(Long id, String title, String content,User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }
    public void updateBoard(String title, String content,User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void setUser(User user){
        this.user = user;
        user.getBoards().add(this);
    }
}
