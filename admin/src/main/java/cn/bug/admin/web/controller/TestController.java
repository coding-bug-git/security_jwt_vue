package cn.bug.admin.web.controller;

import cn.bug.common.controller.BaseController;
import cn.bug.common.core.JwtUtils;
import cn.bug.generator.security_jwt_vue.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 22:49
 */
@Api
@RestController
public class TestController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/test1")
    public Object test1() {
        int a = 10 / 0;
        return "1";
    }

    @GetMapping("/test2")
    public Object test2() {
        return request.getRequestURI();
    }

    @GetMapping("/test3")
    public Object test3() {
        return sysUserService.list();
    }


    @GetMapping("/testToken")
    public Object testToken() {
        return jwtUtils.generatorToken("admin");
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/testRole")
    public Object testRole() {
        return sysUserService.list();
    }

    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/testPermit")
    public Object testPermit() {
        return sysUserService.list();
    }


}
