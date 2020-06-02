package com.spo.service;

import com.spo.domain.User;
import com.spo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(false)
    public void join() {
        User user = User.builder()
                .name("test").build();

        Long savedId = userService.join(user);

        assertEquals(user,userRepository.findOne(savedId));
    }

    // 이거 통과 못한 이유가 fail 떄문인데 이유 알기 IllegalStateException은 왜 안되는걸까 어떻게 하면 통과될까
    // 이름을 같은 걸로 설정해야 테스트가 통과할 수 있다.
    @Test(expected = IllegalStateException.class)
    public void duplicateError(){

        User member1 = User.builder()
                .name("test").build();

        User member2 = User.builder()
                .name("test").build();

        userService.join(member1);
        userService.join(member2);

        fail("예외 발생해야함.");
    }

}