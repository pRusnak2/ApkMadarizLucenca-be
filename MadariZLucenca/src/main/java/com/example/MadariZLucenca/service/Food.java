package com.example.MadariZLucenca.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    private Long foodId;
    private String name;
    private float price;
    private Long restaurantId;
}
