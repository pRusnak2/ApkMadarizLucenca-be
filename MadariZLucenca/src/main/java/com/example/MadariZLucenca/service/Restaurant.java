package com.example.MadariZLucenca.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    private Long restaurantId;
    private String name;
    private String type;
    private int telNumber;
    private String username;
    private String password;
    private String passwordHash;
    private String streetName;
    private String cityName;
    private int postCode;
}
