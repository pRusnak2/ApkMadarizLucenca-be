package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private OrderRepository orderRepository;


    public Long createNewRestaurant(Restaurant restaurant, String roleName) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setName(restaurant.getName());
        entity.setType(restaurant.getType());
        entity.setTelNumber(restaurant.getTelNumber());
        entity.setUsername(restaurant.getUsername());
        entity.setPassword(restaurant.getPassword());
        entity.setStreetName(restaurant.getStreetName());
        entity.setCityName(restaurant.getCityName());
        entity.setPostCode(restaurant.getPostCode());

        RoleEntity restaurantRole = roleRepository.findByName(roleName);
        if (restaurantRole != null) {
            entity.setRoleName(roleName);
        } else {
            System.out.println("chyba s rolami pri restaurant :/");
        }

        restaurantRepository.save(entity);

        LoginService test = new LoginService(loginRepository, roleRepository);
        test.createNewLogin(restaurant, entity);

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

    public void deleteRestaurant(Long id) {
        Optional<RestaurantEntity> opt = restaurantRepository.findById(id);
        if (opt.isEmpty()) {
            return;
        }

        RestaurantEntity restaurant = opt.get();


        List<OrderEntity> orders = orderRepository.findByFoods_RestaurantId(id);
        for (OrderEntity order : orders) {

            orderRepository.delete(order);
        }


        Optional<LoginEntity> loginOpt = loginRepository.findByRestaurant(restaurant);
        loginOpt.ifPresent(loginRepository::delete);


        List<FoodEntity> foods = foodRepository.findByRestaurantId(id);
        for (FoodEntity food : foods) {

            foodRepository.delete(food);
        }

        // Step 4: Delete the restaurant entity
        restaurantRepository.deleteById(id);
    }


}
