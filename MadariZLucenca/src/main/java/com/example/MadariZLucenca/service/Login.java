package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    private Long id;
    private String username;
    private String passwordHash;
    private CustomerEntity customer;
    private RestaurantEntity restaurant;
    private AdminEntity admin;
    private Set<RoleEntity> roles;
}
