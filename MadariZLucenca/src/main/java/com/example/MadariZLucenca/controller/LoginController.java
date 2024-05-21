package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.service.Login;
import com.example.MadariZLucenca.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login/{zakaznikId}")
    public Login loginById(@PathVariable Long Id) {
        return loginService.loginById(Id);
    }

    @DeleteMapping("/login/delete/{Id}")
    public ResponseEntity<String> deleteLoginById(@PathVariable Long Id) {
        loginService.deleteLoginById(Id);
        return ResponseEntity.ok("id login " + Id + " bol uspesne vymazany");
    }
}
