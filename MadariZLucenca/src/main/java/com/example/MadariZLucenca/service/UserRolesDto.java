package com.example.MadariZLucenca.service;

import lombok.Getter;

import java.util.Set;

@Getter
public class UserRolesDto {

    private final String userName;
    private final String role;

    public UserRolesDto(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

}
