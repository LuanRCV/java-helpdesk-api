package com.lrcv.helpdesk.config.security.middlewares;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lrcv.helpdesk.config.security.utils.JWTUtils;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationMiddleware extends BasicAuthenticationFilter {

    public JWTUtils jwtUtils;
    private UserDetailsService userDetailsService;

    public JWTAuthorizationMiddleware(AuthenticationManager authenticationManager, JWTUtils jwtUtils,
            UserDetailsService userDetailsService) {
        super(authenticationManager);

        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken authToken = getAuthentication(header.substring(7));

            if (authToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtils.isValidToken(token)) {
            String username = jwtUtils.getUsername(token);

            UserDetails details = userDetailsService.loadUserByUsername(username);

            return new UsernamePasswordAuthenticationToken(details.getUsername(), null, details.getAuthorities());
        }

        return null;
    }
}
