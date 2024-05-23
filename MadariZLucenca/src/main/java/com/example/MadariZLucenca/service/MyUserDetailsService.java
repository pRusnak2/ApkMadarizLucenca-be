package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.LoginEntity;
import com.example.MadariZLucenca.persistence.LoginRepository;
import com.example.MadariZLucenca.persistence.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LoginEntity> optLoginEntity = loginRepository.findByUsername(username);
        if (optLoginEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        LoginEntity loginEntity = optLoginEntity.get(); // Safe to call get() since we checked for emptiness

        // Convert LoginEntity and roles to UserDetails
        return new MyUserDetails(loginEntity.getUsername(), loginEntity.getPasswordHash(),
                convertRolesToAuthorities(loginEntity.getRoles()));
    }


    private Collection<? extends GrantedAuthority> convertRolesToAuthorities(Set<RoleEntity> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}