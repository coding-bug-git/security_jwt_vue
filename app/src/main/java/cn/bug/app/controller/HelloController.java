package cn.bug.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 16:29
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
