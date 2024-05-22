package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.CustomerEntity;
import com.example.MadariZLucenca.persistence.LoginEntity;
import com.example.MadariZLucenca.persistence.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LoginRepository loginRepository;

    public Long createNewLogin(Customer customer) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(customer.getUsername());
        loginEntity.setPasswordHash(customer.getPasswordHash());
        loginEntity.setCustomer();

        return null;
    }

}
