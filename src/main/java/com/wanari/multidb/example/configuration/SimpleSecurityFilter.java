package com.wanari.multidb.example.configuration;

import com.wanari.multidb.example.domain.view.User;
import com.wanari.multidb.example.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class SimpleSecurityFilter extends GenericFilterBean {

    private final String EMAIL_HEADER = "email";

    private final UserService userService;

    public SimpleSecurityFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //TODO this is not how you want to logout the user, but again this is an example app :)
        SecurityContextHolder.clearContext();

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String email = httpServletRequest.getHeader(EMAIL_HEADER);

        userService.findByEmail(email)
            .peek(this::authorized)
            .onEmpty(() -> unauthorized(servletResponse));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void unauthorized(ServletResponse servletResponse) {
        ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    private void authorized(User user) {
        // you should get your authorities from the user here
        // this is an example app, so we only protect our APIs with valid e-mails
        List<SimpleGrantedAuthority> grantedAuthorities = Collections.emptyList();

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            user.email,
            "",
            grantedAuthorities
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
