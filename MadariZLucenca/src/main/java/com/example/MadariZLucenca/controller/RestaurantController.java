package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.service.Restaurant;
import com.example.MadariZLucenca.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restauracia")
    public Long createNewRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createNewRestaurant(restaurant);
    }

    @GetMapping("/restauracia/{restauraciaId}")
    public Restaurant restaurantById(@PathVariable Long restauraciaId) {
        return restaurantService.restaurantById(restauraciaId);
    }
}
