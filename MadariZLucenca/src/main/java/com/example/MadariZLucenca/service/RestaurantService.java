package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.FoodEntity;
import com.example.MadariZLucenca.persistence.RestaurantEntity;
import com.example.MadariZLucenca.persistence.RestaurantRepository;
import com.example.MadariZLucenca.persistence.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodRepository foodRepository;


    public Long createNewRestaurant(Restaurant restaurant) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setName(restaurant.getName());
        entity.setType(restaurant.getType());
        entity.setTelNumber(restaurant.getTelNumber());
        entity.setUsername(restaurant.getUsername());
        entity.setPassword(restaurant.getPassword());
        entity.setStreetName(restaurant.getStreetName());
        entity.setCityName(restaurant.getCityName());
        entity.setPostCode(restaurant.getPostCode());
        restaurantRepository.save(entity);
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
