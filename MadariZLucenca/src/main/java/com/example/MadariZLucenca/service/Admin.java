package com.example.MadariZLucenca.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Long AdminId;
    private String username;
    private String password;
    private String passwordHash;
    private String roleName;
}
