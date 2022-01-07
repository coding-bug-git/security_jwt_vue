package cn.bug.common.controller;

import cn.bug.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 13:11
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    
    
    @Autowired
    protected RedisCache redisCache;
}
