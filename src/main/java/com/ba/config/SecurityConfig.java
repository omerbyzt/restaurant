package com.ba.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("h2-console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/hi/user").access("hasAnyRole('USER','ADMIN')");
        http.authorizeRequests().antMatchers("/hi/admin").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/**").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/product/list**").access("hasAnyRole('USER','ADMIN')");
        http.authorizeRequests().antMatchers("/product/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/product/delete/**").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/product/update/**").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/order/listall").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/order/add").access("hasAnyRole('USER','ADMIN')");
        http.authorizeRequests().antMatchers("/order/delete/**").access("hasRole('ADMIN')");
        http.httpBasic();
        http.cors();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource);
    }

}
