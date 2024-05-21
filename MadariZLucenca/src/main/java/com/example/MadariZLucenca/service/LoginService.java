package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.CustomerEntity;
import com.example.MadariZLucenca.persistence.CustomerRepository;
import com.example.MadariZLucenca.persistence.LoginEntity;
import com.example.MadariZLucenca.persistence.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    private LoginRepository loginRepository;

    public Long createNewLogin(Login login) {
        LoginEntity entity = new LoginEntity();
        entity.setUsername(login.getUsername());
        entity.setPasswordHash(passwordEncoder.encode(login.getPassword()));
        entity.setRole(login.getRole());
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
        return login;
    }


    public void deleteLoginById(Long id) {
        loginRepository.deleteById(id);
    }
}
