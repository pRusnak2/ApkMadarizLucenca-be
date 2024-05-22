package com.example.MadariZLucenca.service;

import java.util.Set;

public class UserRolesDto {

    private final String userName;
    private final Set<Object> roles;

    public UserRolesDto(String userName, Set<Object> roles) {
        this.userName = userName;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public Set<Object> getRoles() {
        return roles;
    }

}
