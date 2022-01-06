package cn.bug.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 16:28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.bug.**"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
