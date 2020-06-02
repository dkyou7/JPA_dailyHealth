package com.spo.controller;

import com.spo.domain.Board;
import com.spo.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardForm {
    private Long id;
    private String title;
    private String content;
    private User user;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .user(user)
                .build();
        return board;
    }

    @Builder
    public BoardForm(Long id, String title, String content, User user, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}