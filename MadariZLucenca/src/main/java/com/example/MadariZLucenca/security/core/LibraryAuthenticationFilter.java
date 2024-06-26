package com.example.MadariZLucenca.security.core;

import com.example.MadariZLucenca.service.AuthenticationService;
import com.example.MadariZLucenca.service.UserRolesDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    public LibraryAuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if ( !StringUtils.hasLength(authHeader) || ! authHeader.startsWith("Bearer ")) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }

        String token = authHeader.substring("Bearer".length()).trim();
        UserRolesDto userRole = authenticationService.authenticate(token);

        SimpleGrantedAuthority role = new SimpleGrantedAuthority(userRole.getRole());

        UsernamePasswordAuthenticationToken auth
                = new UsernamePasswordAuthenticationToken(role, userRole.getUserName(), null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

}

