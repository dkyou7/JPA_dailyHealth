package com.spo.service;

import com.spo.domain.Board;
import com.spo.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService{
    private final BoardRepository boardRepository;

    @Transactional
    public Long savePost(Board board) {
        return boardRepository.save(board).getId();
    }

    @Transactional
    public Long updatePost(Long id, Board board){
        Board findBoard = boardRepository.findById(board.getId()).get();
        findBoard.updateBoard(board.getTitle(),board.getContent(),board.getUser());
        return findBoard.getId();
     }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    public List<Board> findPosts(){
        return boardRepository.findAll();
    }

    public Board findOne(Long id){
        return boardRepository.findById(id).get();
    }

}
