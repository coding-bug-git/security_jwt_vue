package cn.bug.admin.web.controller;

import cn.bug.common.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 22:49
 */
@RestController
public class TestController {
    
    @GetMapping("test1")
    public Object test1() {
        return "1";
    }
    

}
