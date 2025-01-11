package com.limo.ssiach2ex1;

import com.limo.ssiach2ex1.domain.User;
import com.limo.ssiach2ex1.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SsiaCh2Ex1ApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        Optional<User> admin = userRepository.findByUsername("admin");
        System.out.println(admin.get());
    }

}
