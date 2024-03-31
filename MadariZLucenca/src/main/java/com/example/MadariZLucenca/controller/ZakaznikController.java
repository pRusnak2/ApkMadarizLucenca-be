package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.persistence.ZakaznikEntity;
import com.example.MadariZLucenca.persistence.ZakaznikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
    public  class ZakaznikController {

        @Autowired
        private ZakaznikRepository zakaznikRepository;

    // vlozenie natvrdo. Bude sluzit na registraciu zakaznika
        @PostMapping("/test/zakaznik")                          // treba volat cez postman + openvpn (profil umb). Vysledok sa ulozi do tabulky skuska.
        public String addTestData() {
            ZakaznikEntity zakaznik1 = new ZakaznikEntity();
            zakaznik1.setTel_cislo(522342343);
            zakaznik1.setEmail("kristian.grzanka@gmail.com");
            zakaznik1.setHeslo("halabala");
            zakaznik1.setUlica("mizerna");
            zakaznik1.setMesto("bardejov");
            zakaznik1.setPsc(4544);


            zakaznikRepository.save(zakaznik1);


            return "Hodnoty boli uložené.";
        }


        @GetMapping("/test/zakaznik/{id}") //treba cez get http://localhost:8080/test/zakaznik/1
        public ResponseEntity<ZakaznikEntity> getZakaznikById(@PathVariable Long id) {
            Optional<ZakaznikEntity> zakaznikOptional = zakaznikRepository.findById(id);
            if (zakaznikOptional.isPresent()) {
                ZakaznikEntity zakaznik = zakaznikOptional.get();
                return ResponseEntity.ok().body(zakaznik);
            } else {
                return ResponseEntity.notFound().build();
            }
        }


        @PostMapping("/test1")
        public String testPostRequest() {
            return "test";
        }
    }

