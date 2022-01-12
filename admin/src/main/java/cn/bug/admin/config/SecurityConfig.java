package cn.bug.admin.config;

import cn.bug.admin.security.CaptchaFilter;
import cn.bug.admin.security.JwtAuthenticationFilter;
import cn.bug.admin.security.UserDetailServiceImpl;
import cn.bug.common.core.JwtUtils;
import cn.bug.common.domain.AjaxResult;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
            // "/test2",
            "/testToken",
            "/test3"
    };
    @Autowired
    CaptchaFilter captchaFilter;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserDetailsService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //重写方法
        http.cors().and().csrf().disable()

                // 登录配置
                .formLogin()
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())

                .and()
                .logout()
                // .logoutSuccessHandler(jwtLogoutSuccessHandler)

                // 禁用session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 配置拦截规则
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITE_LIST).permitAll()
                .anyRequest().authenticated()

                // 异常处理器
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())

                // 配置自定义的过滤器
                .and()
                .addFilter(jwtAuthenticationFilter())
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return (req, res, e) -> {
            res.setContentType("application/json;charset=UTF-8");
            ServletOutputStream outputStream = res.getOutputStream();

            AjaxResult result = AjaxResult.fail(e.getMessage());
            e.printStackTrace();

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

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }


    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (req, res, e) -> {
            res.setContentType("application/json;charset=UTF-8");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ServletOutputStream outputStream = res.getOutputStream();
            AjaxResult result = AjaxResult.fail(HttpServletResponse.SC_UNAUTHORIZED,"请先登录",null);
            outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        };
    }


    private AccessDeniedHandler accessDeniedHandler() {
        return (req, res, e) -> {
            res.setContentType("application/json;charset=UTF-8");
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ServletOutputStream outputStream = res.getOutputStream();
            AjaxResult result = AjaxResult.fail(e.getMessage());
            outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        };
    }
}
