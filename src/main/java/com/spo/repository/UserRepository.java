package com.spo.repository;

import com.spo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user){
        em.persist(user);
    }
    public User findOne(Long id){
        return em.find(User.class, id);
    }
    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
    public List<User> findByCardNumber(int cardNum){
        return em.createQuery("select u from Member u where u.cardNum = :cardNum",User.class)
                .setParameter("cardNum",cardNum)
                .getResultList();
    }
}
