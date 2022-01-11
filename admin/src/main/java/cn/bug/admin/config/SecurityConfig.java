package cn.bug.admin.config;

import cn.bug.admin.security.CaptchaFilter;
import cn.bug.common.core.JwtUtils;
import cn.bug.common.domain.AjaxResult;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 15:00
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private static final String[] URL_WHITE_LIST = {
            "/login",
            "/logout",
            "/captcha",
            "/favicon.ico",
            "/test1",
            "/test2",
            "/test3"
    };
    @Autowired
    CaptchaFilter captchaFilter;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //重写方法
        http.cors().and().csrf().disable()
                .formLogin()
                .failureHandler(loginFailureHandler())
                .successHandler(loginSuccessHandler())

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITE_LIST).permitAll()
                .anyRequest().authenticated()

                .and()
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)

        ;

    }

    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return (req, res, e) -> {
            res.setContentType("application/json;charset=UTF-8");
            ServletOutputStream outputStream = res.getOutputStream();

            AjaxResult result = AjaxResult.fail(e.getMessage());

            outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));

            outputStream.flush();
            outputStream.close();
        };
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (req, res, authentication) -> {
            res.setContentType("application/json;charset=UTF-8");
            ServletOutputStream outputStream = res.getOutputStream();

            String jwt = jwtUtils.generatorToken(authentication.getName());
            res.setHeader(jwtUtils.getHeader(), jwt);


            AjaxResult result = AjaxResult.success("11");

            outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));

            outputStream.flush();
            outputStream.close();

        };
    }


}
