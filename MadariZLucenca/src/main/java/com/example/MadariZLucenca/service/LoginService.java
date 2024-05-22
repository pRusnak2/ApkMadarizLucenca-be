package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.security.core.SecurityConfig;
import com.example.MadariZLucenca.persistence.CustomerEntity;
import com.example.MadariZLucenca.persistence.LoginEntity;
import com.example.MadariZLucenca.persistence.LoginRepository;
import com.example.MadariZLucenca.persistence.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LoginRepository loginRepository;

    public Long createNewLogin(Customer customer, CustomerEntity customerEntity) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(customer.getUsername());
        loginEntity.setPasswordHash(passwordEncoder.encode(customer.getPassword()));
        loginEntity.setCustomer(customerEntity);

        loginRepository.save(loginEntity);
        return loginEntity.getId();
    }

    public Long createNewLogin(Restaurant restaurant, RestaurantEntity restaurantEntity){
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(restaurant.getUsername());
        loginEntity.setPasswordHash(passwordEncoder.encode(restaurant.getPassword()));
        loginEntity.setRestaurant(restaurantEntity);

        loginRepository.save(loginEntity);
        return loginEntity.getId();
    }

}
