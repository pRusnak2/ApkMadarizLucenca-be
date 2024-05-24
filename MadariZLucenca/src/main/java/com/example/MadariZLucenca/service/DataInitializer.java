package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.RoleEntity;
import com.example.MadariZLucenca.persistence.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(new RoleEntity(1L, "ADMIN"));
            roleRepository.save(new RoleEntity(2L, "RESTAURANT"));
            roleRepository.save(new RoleEntity(3L, "CUSTOMER"));
        }
    }
}
