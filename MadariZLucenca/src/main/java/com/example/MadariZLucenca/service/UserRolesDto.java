package com.example.MadariZLucenca.service;

import java.util.Set;

public class UserRolesDto {

    private final String userName;
    private final String role;

    public UserRolesDto(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

}
