package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.persistence.ZakaznikEntity;
import com.example.MadariZLucenca.persistence.ZakaznikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    public  class ZakaznikController {

        @Autowired
        private ZakaznikRepository zakaznikRepository;

        @PostMapping("/test/addData")                          // treba volat cez postman + openvpn (profil umb). Vysledok sa ulozi do tabulky skuska.
        public String addTestData() {
            ZakaznikEntity zakaznik1 = new ZakaznikEntity();
            zakaznik1.setMeno("nepoviem");
            zakaznik1.setHeslo("halabala");

            zakaznikRepository.save(zakaznik1);


            return "Hodnoty boli uložené.";
        }

        @PostMapping("/test1")
        public String testPostRequest() {
            return "test";
        }
    }

