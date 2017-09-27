package com.wanari.multidb.example.configuration;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SimpleSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final SimpleSecurityFilter simpleSecurityFilter;

    public SimpleSecurityConfigurerAdapter(SimpleSecurityFilter simpleSecurityFilter) {
        this.simpleSecurityFilter = simpleSecurityFilter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(simpleSecurityFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
