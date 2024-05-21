package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private LoginRepository loginRepository;


    public Long createNewRestaurant(Restaurant restaurant) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setName(restaurant.getName());
        entity.setType(restaurant.getType());
        entity.setTelNumber(restaurant.getTelNumber());
        entity.setUsername(restaurant.getUsername());
        entity.setPassword(restaurant.getPassword());
        entity.setPasswordHash(passwordEncoder.encode(restaurant.getPassword()));
        entity.setStreetName(restaurant.getStreetName());
        entity.setCityName(restaurant.getCityName());
        entity.setPostCode(restaurant.getPostCode());
        restaurantRepository.save(entity);

        LoginEntity repositoryEntity = new LoginEntity();
        repositoryEntity.setUsername(restaurant.getUsername());
        repositoryEntity.setPasswordHash(passwordEncoder.encode(restaurant.getPassword()));
        repositoryEntity.setRole(3);
        repositoryEntity.setRestaurant(entity);
        repositoryEntity.setRestaurant(null);
        loginRepository.save(repositoryEntity);

        return entity.getRestaurantId();
    }


    public Restaurant restaurantById(Long id) {
        Optional<RestaurantEntity> opt = restaurantRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        RestaurantEntity entity = opt.get();
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(entity.getRestaurantId());
        restaurant.setName(entity.getName());
        restaurant.setType(entity.getType());
        restaurant.setTelNumber(entity.getTelNumber());
        restaurant.setUsername(entity.getUsername());
        restaurant.setPassword(entity.getPassword());
        restaurant.setPasswordHash(entity.getPasswordHash());
        restaurant.setStreetName(entity.getStreetName());
        restaurant.setCityName(entity.getCityName());
        restaurant.setPostCode(entity.getPostCode());
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        List<RestaurantEntity> entities = restaurantRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    Restaurant restaurant = new Restaurant();
                    restaurant.setRestaurantId(entity.getRestaurantId());
                    restaurant.setName(entity.getName());
                    restaurant.setType(entity.getType());
                    restaurant.setTelNumber(entity.getTelNumber());
                    restaurant.setUsername(entity.getUsername());
                    restaurant.setPassword(entity.getPassword());
                    restaurant.setPasswordHash(entity.getPasswordHash());
                    restaurant.setStreetName(entity.getStreetName());
                    restaurant.setCityName(entity.getCityName());
                    restaurant.setPostCode(entity.getPostCode());
                    return restaurant;
                })
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    public void deleteRestaurant(Long id) {

        Optional<RestaurantEntity> opt = restaurantRepository.findById(id);
        if (opt.isEmpty()) {
            return;
        }

        List<FoodEntity> foods = foodRepository.findByRestaurantId(id);

        for (FoodEntity food : foods) {
            foodRepository.delete(food);
        }

        restaurantRepository.deleteById(id);
    }

}
