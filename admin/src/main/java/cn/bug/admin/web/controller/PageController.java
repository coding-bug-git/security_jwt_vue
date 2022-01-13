package cn.bug.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description
 *
 * @author by bug
 * @Date 2021/11/23
 */
@Controller
public class PageController {
    @GetMapping(value = {"/index", ""})
    public String index() {
        return "redirect:swagger-ui/";
    }
}
