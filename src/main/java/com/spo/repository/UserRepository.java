package com.spo.repository;

import com.spo.domain.User;
import com.spo.signuplogin.domain.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
        return em.createQuery("select u from User u where u.cardNum = :cardNum",User.class)
                .setParameter("cardNum",cardNum)
                .getResultList();
    }
    public List<User> findByEmail(String userEmail){
        return em.createQuery("select u from User u where u.email = :email",User.class)
                .setParameter("email",userEmail)
                .getResultList();
    }
    public void deleteById(Long id){
        em.remove(id);
    }
}
