package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    FoodRepository jedloRepository;

    @Autowired
    AlergenyRepository alergenyRepository;


    public Long createNewFood(Food food) {
        Long restaurantId = food.getRestaurantId();

        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setName(food.getName());
        foodEntity.setDescription(food.getDescription());
        foodEntity.setPrice(food.getPrice());
        foodEntity.setRestaurantId(restaurantId);

        List<String> allergenIds = food.getAllergens();
        Set<AlergenyEntity> allergens = new HashSet<>();
        for (String allergenId : allergenIds) {
            Optional<AlergenyEntity> allergenOpt = alergenyRepository.findById(Long.valueOf(allergenId));
            allergenOpt.ifPresent(allergens::add);
        }
        foodEntity.setAllergens(allergens);

        FoodEntity savedFoodEntity = jedloRepository.save(foodEntity);
        return savedFoodEntity.getFoodId();
    }

    public Food FoodById(Long id) {
        Optional<FoodEntity> opt = jedloRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }

        FoodEntity entity = opt.get();
        Food food = new Food();

        food.setFoodId(entity.getFoodId());
        food.setName(entity.getName());
        food.setPrice(entity.getPrice());

        return food;
    }

    public List<Food> FoodByRestaurantId(Long restaurantId) {
        List<FoodEntity> foodEntities = jedloRepository.findByRestaurantId(restaurantId);
        List<Food> foodList = foodEntities.stream().map(entity -> {
            Food food = new Food();
            food.setFoodId(entity.getFoodId());
            food.setName(entity.getName());
            food.setDescription(entity.getDescription());
            food.setPrice(entity.getPrice());
            food.setRestaurantId(entity.getRestaurantId());

            Set<AlergenyEntity> allergens = entity.getAllergens();
            List<String> allergenNamesWithId = allergens.stream()
                    .map(allergen -> " " + allergen.getAllergenId() + " - " + allergen.getName())
                    .collect(Collectors.toList());

            food.setAllergens(allergenNamesWithId);
            return food;
        }).collect(Collectors.toList());

        return foodList;
    }

    public Food deleteFoodById(Long id) {
        Optional<FoodEntity> opt = jedloRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }

        FoodEntity entity = opt.get();
        Food deletedFood = new Food();

        deletedFood.setFoodId(entity.getFoodId());
        deletedFood.setName(entity.getName());
        deletedFood.setDescription(entity.getDescription());
        deletedFood.setPrice(entity.getPrice());

        jedloRepository.deleteById(id);

        return deletedFood;
    }
}
