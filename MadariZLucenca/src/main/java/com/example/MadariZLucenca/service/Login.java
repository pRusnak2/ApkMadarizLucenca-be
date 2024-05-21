package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.CustomerEntity;
import com.example.MadariZLucenca.persistence.RestaurantEntity;
import com.example.MadariZLucenca.persistence.RoleEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    private Long id;
    private String username;
    private String password;
    private RoleEntity role;
    private CustomerEntity customer;
    private RestaurantEntity restaurant;
}
