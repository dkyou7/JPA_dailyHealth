package com.spo.service;

import com.spo.domain.User;
import com.spo.repository.UserRepository;
import com.spo.signuplogin.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user){
        // 비밀번호 암호화 코드
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        validateDuplicateMember(user);
        userRepository.save(user);  // DB에 값이 보장이 된다.
        return user.getId();
    }

    private void validateDuplicateMember(User user){
        List<User> findUser = userRepository.findByCardNumber(user.getCardNum());
        if(!findUser.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }

    @Transactional
    public void update(Long id,String name){
        User mem = userRepository.findOne(id);
        mem.setName(name);
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
