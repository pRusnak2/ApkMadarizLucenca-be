package com.example.MadariZLucenca.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    private Long foodId;
    private String name;
    private String description;
    private float price;
    private Long restaurantId;
    private List<String> allergens;
}
