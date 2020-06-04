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
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user){
        // 비밀번호 암호화 코드
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // validateDuplicateMember(user);
        userRepository.save(user);  // DB에 값이 보장이 된다.
        return user.getId();
    }

    private void validateDuplicateMember(User user){
        User findUser = userRepository.findByCardNum(user.getCardNum()).get();
        System.out.println("findUser = " + findUser);
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void update(Long id,String name){
        User mem = userRepository.findById(id).get();
        mem.setName(name);
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(userEmail).get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("dkyou7@naver.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    @Transactional
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
