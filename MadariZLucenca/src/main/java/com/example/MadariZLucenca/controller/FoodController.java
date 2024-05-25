package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private AuthenticationService authenticationService;

    private final RoleService roleService;

    public FoodController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/food")
    @HasRole("RESTAURANT")
    public Food createFood(@RequestHeader("Authorization") String authHeader, @RequestBody Food food) {
        String token = authHeader.substring("Bearer ".length()).trim();
        UserRolesDto userRolesDto = authenticationService.authenticate(token);

        Long restaurantId = authenticationService.getRestaurantIdByUsername(userRolesDto.getUserName());

        food.setRestaurantId(restaurantId);

        Long createdFoodId = foodService.createNewFood(food);

        return foodService.FoodById(createdFoodId);
    }

    @GetMapping("/food/{foodId}")
    public Food foodById(@PathVariable Long foodId) {
        return foodService.FoodById(foodId);
    }

    @GetMapping("/food/restaurant/{restaurantId}")
    public List<Food> foodByRestaurantId(@PathVariable Long restaurantId) {
        return foodService.FoodByRestaurantId(restaurantId);
    }

    @DeleteMapping("/food/vymazanie/{foodId}")
    public Food deleteFoodById(@PathVariable Long foodId) {
        return foodService.deleteFoodById(foodId);
    }
}
