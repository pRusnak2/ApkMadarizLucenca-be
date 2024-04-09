package com.example.MadariZLucenca.service;

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
    private String streetName;
    private String cityName;
    private int postCode;
}
