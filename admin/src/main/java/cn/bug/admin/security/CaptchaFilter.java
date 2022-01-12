package cn.bug.admin.security;

import cn.bug.admin.exception.CaptchaException;
import cn.bug.common.core.redis.RedisUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 10:48
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AuthenticationFailureHandler loginFailureHandler;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String url = req.getRequestURI();
        if ((contextPath + "/login").equals(url) && req.getMethod().equalsIgnoreCase("POST")) {
            try {
                validate(req);
            } catch (CaptchaException e) {
                loginFailureHandler.onAuthenticationFailure(req, res, e);
            }
        }
        filterChain.doFilter(req, res);
    }

    private void validate(HttpServletRequest req) {
        String captcha = req.getParameter("captcha");
        String token = req.getParameter("token");
        if (StrUtil.isBlank(captcha) || StrUtil.isBlank(token)) {
            throw new CaptchaException("验证码错误");
        }

        if (!captcha.equals(redisUtil.hget(token, "captcha"))) {
            throw new CaptchaException("验证码错误");
        }

        redisUtil.hdel(token, "captcha");
    }
}
