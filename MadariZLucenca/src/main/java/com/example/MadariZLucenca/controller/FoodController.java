package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.service.Food;
import com.example.MadariZLucenca.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class FoodController {
    @Autowired
    private FoodService foodService;

    @PostMapping("/food")
    public Long createNewFood(@RequestBody Food food) {
        return foodService.createNewFood(food);
    }

    @GetMapping("/food/{foodId}")
    public Food foodById(@PathVariable Long foodId) {
        return foodService.FoodById(foodId);
    }
}
