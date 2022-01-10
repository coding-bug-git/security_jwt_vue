package cn.bug.common.core.redis.exception_handler;

import cn.bug.common.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 11:25
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handler(RuntimeException e) {
        // e.printStackTrace();
        log.error("Assert异常：----------{}",e.getMessage());
        return AjaxResult.fail(e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public AjaxResult handler(IllegalArgumentException e) {
        log.error("Assert异常：----------{}",e.getMessage());
        return AjaxResult.fail(e.getMessage());
    }
}
