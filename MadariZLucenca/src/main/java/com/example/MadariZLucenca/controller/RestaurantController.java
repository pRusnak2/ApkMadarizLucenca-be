package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.persistence.RestaurantEntity;
import com.example.MadariZLucenca.persistence.RestaurantRepository;
import com.example.MadariZLucenca.service.Restaurant;
import com.example.MadariZLucenca.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restauracia")
    public Long vytvorNovuRestauraciu(@RequestBody Restaurant restaurant) {
        return restaurantService.createNewRestaurant(restaurant);
    }

    @GetMapping("/restauracia/{restauraciaId}")
    public Restaurant restauraciaPodlaId(@PathVariable Long restauraciaId) {
        return restaurantService.restaurantById(restauraciaId);
    }
}
