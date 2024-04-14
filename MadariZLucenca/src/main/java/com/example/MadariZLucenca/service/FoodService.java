package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    FoodRepository jedloRepository;
    public Long createNewFood(Food food) {
        FoodEntity entity = new FoodEntity();

        entity.setName(food.getName());
        entity.setFoodId(food.getFoodId());
        entity.setPrice(food.getPrice());

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

    public Food deleteFoodById(Long id) {
        Optional<FoodEntity> opt = jedloRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }

        FoodEntity entity = opt.get();
        Food deletedFood = new Food();

        deletedFood.setFoodId(entity.getFoodId());
        deletedFood.setName(entity.getName());
        deletedFood.setPrice(entity.getPrice());

        jedloRepository.deleteById(id);

        return deletedFood;
    }

}
