package cn.bug.admin.web.controller;

import cn.bug.common.controller.BaseController;
import cn.bug.common.domain.AjaxResult;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 13:10
 */
@RestController
public class AuthController extends BaseController {
    @Autowired
    Producer kaptchaProducer;

    @GetMapping("/captcha")
    public AjaxResult captcha() throws IOException {
        String token = UUID.randomUUID().toString();
        String captcha = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(captcha);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        String encode = Base64Encoder.encode(outputStream.toByteArray());
        request.getMethod();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encode;
        redisUtil.hset(token,"captcha", captcha, 120);
        return AjaxResult.success(
                MapUtil.builder()
                        .put("token", token)
                        .put("captchaImg",base64Img)
                        .build()
        );
    }
}
