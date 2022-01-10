package cn.bug.common.domain;


import cn.bug.common.domain.constant.HttpStatus;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;

/**
 * 操作消息提醒
 *
 * @author bug
 */
@Data
public class AjaxResult {
    private int code;
    private String msg;
    private Object data;

    public static AjaxResult success(Object data) {
        return success(200, "操作成功", data);
    }

    public static AjaxResult success(int code, String msg, Object data) {
        AjaxResult r = new AjaxResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static AjaxResult fail(String msg) {
        return fail(400, msg, null);
    }

    public static AjaxResult fail(int code, String msg, Object data) {
        AjaxResult r = new AjaxResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
