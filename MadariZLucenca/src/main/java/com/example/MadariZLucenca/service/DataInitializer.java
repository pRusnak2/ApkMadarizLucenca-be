package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class DataInitializer {

    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    AdminRepository adminRepository;

    @PostConstruct
    public void initRoles() {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(new RoleEntity(1L, "ADMIN"));
            roleRepository.save(new RoleEntity(2L, "RESTAURANT"));
            roleRepository.save(new RoleEntity(3L, "CUSTOMER"));
        }
    }

    @PostConstruct
    public void initAdmins() {
        if(adminRepository.findAll().isEmpty()) {
            Long AdminId = 1L;
            String username = "admin_1";
            String password = "admin_1";
            String passwordHash = passwordEncoder.encode(password);
            String roleName = "ADMIN";
            AdminEntity entity = new AdminEntity(AdminId, username, password, passwordHash, roleName);
            adminRepository.save(entity);

            LoginService test = new LoginService(loginRepository, roleRepository);
            test.createNewLogin(entity);
        }
    }
}
