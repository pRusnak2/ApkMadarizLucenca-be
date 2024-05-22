package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long userId;
    private String username;
    private int telNumber;
    private String email;
    private String password;
    private String passwordHash;
    private String streetName;
    private String cityName;
    private int postCode;
    private String firstName;
    private String lastName;
}

