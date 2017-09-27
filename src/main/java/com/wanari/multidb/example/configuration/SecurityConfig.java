package com.wanari.multidb.example.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SimpleSecurityConfigurerAdapter simpleSecurityConfigurerAdapter;

    public SecurityConfig(SimpleSecurityConfigurerAdapter simpleSecurityConfigurerAdapter) {
        this.simpleSecurityConfigurerAdapter = simpleSecurityConfigurerAdapter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/hello/**").authenticated()
            .and()
            .apply(simpleSecurityConfigurerAdapter);
    }
}
