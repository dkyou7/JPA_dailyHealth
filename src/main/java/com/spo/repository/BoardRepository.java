package com.spo.repository;

import com.spo.domain.Board;
import com.spo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Board save(Board board){
        em.persist(board);
        return board;
    }
    public Board findOne(Long id){
        return em.find(Board.class, id);
    }

    public List<Board> findAll(){
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }
    public List<Board> findByCardNumber(int cardNum){
        return em.createQuery("select u from Board u where u.cardNum = :cardNum",Board.class)
                .setParameter("cardNum",cardNum)
                .getResultList();
    }
}