package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.AdminEntity;
import com.example.MadariZLucenca.persistence.CustomerEntity;
import com.example.MadariZLucenca.persistence.LoginEntity;
import com.example.MadariZLucenca.persistence.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
