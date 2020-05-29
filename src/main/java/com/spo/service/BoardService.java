package com.spo.service;

import com.spo.domain.Board;
import com.spo.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    @Transactional
    public Long updatePost(Board board){
        Board new_board = boardRepository.findOne(board.getId());
        new_board.updateBoard(board.getTitle(),board.getContent());
        return new_board.getId();
     }

    public List<Board> findPosts(){
        return boardRepository.findAll();
    }

    public Board findOne(Long id){
        return boardRepository.findOne(id);
    }
}
