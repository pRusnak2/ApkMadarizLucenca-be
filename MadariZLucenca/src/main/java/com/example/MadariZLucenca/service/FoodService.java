package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    FoodRepository jedloRepository;

    @Autowired
    AlergenyRepository alergenyRepository;

    //@PreAuthorize("hasRole('ROLE_RESTAURANT')")
    public Long createNewFood(Food food) {
        FoodEntity entity = new FoodEntity();

        entity.setName(food.getName());
        entity.setDescription(food.getDescription());
        entity.setFoodId(food.getFoodId());
        entity.setRestaurantId(food.getRestaurantId());
        entity.setPrice(food.getPrice());

        List<String> allergenIds = food.getAllergens();
        Set<AlergenyEntity> allergens = new HashSet<>();

        for (String allergenId : allergenIds) {
            Optional<AlergenyEntity> allergenOpt = alergenyRepository.findById(Long.valueOf(allergenId));
            if (allergenOpt.isPresent()) {
                allergens.add(allergenOpt.get());
            }
        }

        entity.setAllergens(allergens);

        jedloRepository.save(entity);

        return entity.getFoodId();
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

    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
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
