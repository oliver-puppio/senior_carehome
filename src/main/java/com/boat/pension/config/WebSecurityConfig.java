package com.boat.pension.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/v2/*").permitAll()
//                .antMatchers("/csrf").permitAll()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//        ;
        //手动关闭csrf请求
        http.cors().and().csrf().disable();
    }

}

