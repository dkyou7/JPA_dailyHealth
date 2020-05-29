package com.spo.service;

import com.spo.domain.Board;
import com.spo.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Transactional
    public Long savePost(Board board) {
        return boardRepository.save(board).getId();
    }

    public List<Board> findPosts(){
        return boardRepository.findAll();
    }
}
