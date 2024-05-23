package com.example.MadariZLucenca.service;


import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private static final int TOKEN_VALIDITY_IN_MINUTES = 15;
    private final LoginRepository loginRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Autowired
    public AuthenticationService(LoginRepository loginRepository, TokenRepository tokenRepository, RoleRepository roleRepository) {
        this.loginRepository = loginRepository;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public String authenticate(String username, String password) {
        Optional<LoginEntity> optionalUser = loginRepository.findByUsername(username);


        if (optionalUser.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Username and/or password do not match!");
        }

        if (!passwordEncoder.matches(password, optionalUser.get().getPasswordHash())) {
            throw new AuthenticationCredentialsNotFoundException("Username and/or password do not match!");
        }

        TokenEntity token = new TokenEntity();
        String randomString = UUID.randomUUID().toString();
        token.setToken(randomString);

        if(loginRepository.findByUsername(username).get().getCustomer() != null){
            token.setCustomer(optionalUser.get().getCustomer());
        } else {
            token.setRestaurant(optionalUser.get().getRestaurant());
        }

        token.setCreatedAt(LocalDateTime.now());
        tokenRepository.save(token);

        return token.getToken();
    }

    @Transactional
    public UserRolesDto authenticate(String token) {
        Optional<TokenEntity> optionalToken = tokenRepository.findByToken(token);

        if (optionalToken.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }

        validateTokenExpiration(optionalToken.get());

        RoleEntity role;
        if(optionalToken.get().getRestaurant() == null){
            role = roleRepository.findByName(optionalToken.get().getCustomer().getRoleName());
            String roleName = role.getRoleName();
            return new UserRolesDto(optionalToken.get().getCustomer().getUsername(), roleName);
        } else {
            role = roleRepository.findByName(optionalToken.get().getRestaurant().getRoleName());
            String roleName = role.getRoleName();
            return new UserRolesDto(optionalToken.get().getRestaurant().getUsername(), roleName);
        }

    }

    private void validateTokenExpiration(TokenEntity token) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenExpiration = token.getCreatedAt().plus(TOKEN_VALIDITY_IN_MINUTES, ChronoUnit.MINUTES);

        if (now.isAfter(tokenExpiration)) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }
    }

    @Transactional
    public void tokenRemove(String token) {
        tokenRepository.deleteByToken(token);
    }

}