package cn.bug.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 16:35
 */
@Slf4j
@SpringBootTest
public class TmpTest {
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Test
    void passwordTest() {
        String encode = bCryptPasswordEncoder.encode("123456");
        boolean matches = bCryptPasswordEncoder.matches("123456", encode);
        System.out.println("matches = " + matches);
        System.out.println("encode = " + encode);
        assert matches;
    }
// $2a$10$eoIq6TIDboNEw5IY1hoXzuhRe/sTUbBJJK39rqWiwOy1cepYCBsp6
// $2a$10$dMBlqtYVMn8bB1Wp.yJ9B.eFVT9VGNsWWTBHsjKo.3DjoUdvhcwPq
}
