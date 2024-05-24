package com.example.MadariZLucenca.security.core;

import com.example.MadariZLucenca.service.AuthenticationService;
import com.example.MadariZLucenca.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private LibraryAuthenticationEntryPoint authEntryPoint;

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/authentication").permitAll()
                .anyRequest().authenticated());
//        http.exceptionHandling((exception)-> exception.authenticationEntryPoint(authEntryPoint).accessDeniedPage("/error/accedd-denied"));
        http.exceptionHandling((exception)-> exception.authenticationEntryPoint(authEntryPoint));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(new LibraryAuthenticationFilter(authenticationService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/authentication")
                .and().ignoring().requestMatchers("/h2-console/*")
                .and().ignoring().requestMatchers("/zakaznik")
                .and().ignoring().requestMatchers("/prihlasenie")
                .and().ignoring().requestMatchers("/restauracia/*")
                .and().ignoring().requestMatchers("/restauracia")
                .and().ignoring().requestMatchers("/restauracia-formular/*")
                .and().ignoring().requestMatchers("/kontakt")
                .and().ignoring().requestMatchers("/food/restaurant/{restaurantId}")
                .and().ignoring().requestMatchers("/")
//                .and().ignoring().requestMatchers("/food/*")
                .and().ignoring().requestMatchers("/orders/vymazanie/{orderId}")
                .and().ignoring().requestMatchers("/orders/update/{orderId}")
                .and().ignoring().requestMatchers("/orders/restauracia");

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Example using BCryptPasswordEncoder
    }
}

