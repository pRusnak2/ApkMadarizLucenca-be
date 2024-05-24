package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import com.example.MadariZLucenca.security.core.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final LoginRepository loginRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository, RoleRepository roleRepository) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.loginRepository = loginRepository;
        this.roleRepository = roleRepository;
    }
    public Long createNewLogin(Customer customer, CustomerEntity customerEntity) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(customer.getUsername());
        loginEntity.setPasswordHash(passwordEncoder.encode(customer.getPassword()));
        loginEntity.setCustomer(customerEntity);

        RoleEntity customerRole = roleRepository.findByName("CUSTOMER"); // Get customer role
        if (customerRole != null) {
            Set<RoleEntity> roles = new HashSet<>(); // Create a new Set
            roles.add(customerRole);
            loginEntity.setRoles(roles);
        } else {
            System.out.println("rola nebola nájdená/neexistuje");
        }

        loginRepository.save(loginEntity);
        return loginEntity.getId();
    }

    public Long createNewLogin(Restaurant restaurant, RestaurantEntity restaurantEntity){
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(restaurant.getUsername());
        loginEntity.setPasswordHash(passwordEncoder.encode(restaurant.getPassword()));
        loginEntity.setRestaurant(restaurantEntity);

        RoleEntity restaurantRole = roleRepository.findByName("RESTAURANT");
        if (restaurantRole != null) {
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(restaurantRole);
            loginEntity.setRoles(roles);
        } else {
            System.out.println("rola nebola nájdená/neexistuje");
        }

        loginRepository.save(loginEntity);
        return loginEntity.getId();
    }

    public Long createNewLogin(AdminEntity adminEntity) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(adminEntity.getUsername());
        loginEntity.setPasswordHash(passwordEncoder.encode(adminEntity.getPassword()));
        loginEntity.setAdmin(adminEntity);

        RoleEntity adminRole = roleRepository.findByName("ADMIN");
        if(adminRole != null) {
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(adminRole);
            loginEntity.setRoles(roles);
        } else {
            System.out.println("rola nebola nájdená/neexistuje");
        }

        loginRepository.save(loginEntity);
        return loginEntity.getId();
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
        login.setPasswordHash(entity.getPasswordHash());
        login.setCustomer(entity.getCustomer());
        login.setRestaurant(entity.getRestaurant());
        return login;
    }


    public void deleteLoginById(Long id) {
        loginRepository.deleteById(id);
    }
}
