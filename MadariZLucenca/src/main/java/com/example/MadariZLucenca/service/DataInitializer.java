package com.example.MadariZLucenca.service;

import com.beust.ah.A;
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
    private AdminRepository adminRepository;

    @Autowired
    private AlergenyRepository alergenyRepository;

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

    @PostConstruct
    public void iniAllergens() {
        if(alergenyRepository.findAll().isEmpty()) {
            alergenyRepository.save(new AlergenyEntity(1L, "Obilniny obsahujúce glutén"));
            alergenyRepository.save(new AlergenyEntity(2L, "Kôrovce a výrobky z kôrovcov"));
            alergenyRepository.save(new AlergenyEntity(3L, "Vajcia a výrobky z vajec"));
            alergenyRepository.save(new AlergenyEntity(4L, "Ryby a výrobky z rýb"));
            alergenyRepository.save(new AlergenyEntity(5L, "Arašidy a výrobky z arašidov"));
            alergenyRepository.save(new AlergenyEntity(6L, "Sójové bôby a výrobky zo sójových bôbov"));
            alergenyRepository.save(new AlergenyEntity(7L, "Mlieko a mliečne výrobky"));
            alergenyRepository.save(new AlergenyEntity(8L, "Orechy"));
            alergenyRepository.save(new AlergenyEntity(9L, "Zeler a výrobky zo zeleru"));
            alergenyRepository.save(new AlergenyEntity(10L, "Horčica a výrobky z horčice"));
            alergenyRepository.save(new AlergenyEntity(11L, "Sezamové semeno"));
            alergenyRepository.save(new AlergenyEntity(12L, "Oxid siričitý a siričitany"));
            alergenyRepository.save(new AlergenyEntity(13L, "Vlčí bôb a výrobky z vlčieho bôbu"));
            alergenyRepository.save(new AlergenyEntity(14L, "Mäkkýše a výrobky z mäkkýšov"));
        }
    }
}
