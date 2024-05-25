package com.example.MadariZLucenca.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    public boolean hasRole(String requiredRole, Login login) {
        System.out.println("som vo vnútri hasRole v RoleService");
        if (login != null && login.getRoles() != null) {
            System.out.println("login != null && login.getRoles() != null bolo vyhodnotené ako true");
            for (GrantedAuthority authority : login.getRoles()) {
                if (authority.getAuthority().equals(requiredRole)) {
                    return true;
                }
            }
        }
        System.out.println("login != null && login.getRoles() != null bolo vyhodnotené ako false");
        return false;
    }
}
