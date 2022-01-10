package cn.bug.admin.web.controller;

import cn.bug.generator.security_jwt_vue.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 22:49
 */
@RestController
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/test1")
    public Object test1() {
        int a = 10/0;
        return "1";
    }

    @GetMapping("/test2")
    public Object test2() {
        return sysUserService.list();
    }

    @GetMapping("/test3")
    public Object test3(String a) {
        return a;
    }
}
