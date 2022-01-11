package cn.bug.admin.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 10:55
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg) {
        super(msg);
    }
}
