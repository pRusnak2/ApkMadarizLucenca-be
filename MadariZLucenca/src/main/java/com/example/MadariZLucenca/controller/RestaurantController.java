package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.service.Restaurant;
import com.example.MadariZLucenca.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/restauracia/vsetky")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }



    @DeleteMapping("/restauracia/vymazanie/{restauraciaId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long restauraciaId) {
        restaurantService.deleteRestaurant(restauraciaId);
        return ResponseEntity.ok().build();
    }


}
