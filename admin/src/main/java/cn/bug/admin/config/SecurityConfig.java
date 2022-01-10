package cn.bug.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .formLogin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITE_LIST).permitAll()
                .anyRequest().authenticated()

        ;
    }
}
