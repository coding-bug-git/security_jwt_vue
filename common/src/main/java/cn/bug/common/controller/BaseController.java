package cn.bug.common.controller;

import cn.bug.common.core.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 13:11
 */

public class BaseController {
    @Autowired(required = false)
    protected HttpServletRequest request;


    @Autowired
    protected RedisUtil redisUtil;
}
