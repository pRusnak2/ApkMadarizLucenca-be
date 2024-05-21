package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Service
public class LoginService {

    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Long createNewLogin(Customer customer, CustomerEntity customerEntity) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("customer");
        roleRepository.save(roleEntity);

        LoginEntity entity = new LoginEntity();
        entity.setUsername(customer.getUsername());
        entity.setPasswordHash(passwordEncoder.encode(customer.getPassword()));
        entity.setRole(roleEntity);

        entity.setCustomer(customerEntity);
        entity.setRestaurant(null);

        loginRepository.save(entity);
        return entity.getId();
    }

    public Long createNewLogin(Restaurant restaurant, RestaurantEntity restaurantEntity) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("customer");


        LoginEntity entity = new LoginEntity();
        entity.setUsername(restaurant.getUsername());
        entity.setPasswordHash(passwordEncoder.encode(restaurant.getPassword()));
        entity.setRole(roleEntity);
        entity.setRestaurant(restaurantEntity);
        entity.setRestaurant(null);
        loginRepository.save(entity);
        return entity.getId();
    }


    public Login loginById(Long id){
        Optional<LoginEntity> opt = loginRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        LoginEntity entity = opt.get();
        Login login = new Login();
        login.setId(entity.getId());
        login.setUsername(entity.getUsername());
        login.setPassword(entity.getPasswordHash());
        login.setRole(entity.getRole());
        login.setCustomer(entity.getCustomer());
        login.setRestaurant(entity.getRestaurant());
        return login;
    }


    public void deleteLoginById(Long id) {
        loginRepository.deleteById(id);
    }
}
