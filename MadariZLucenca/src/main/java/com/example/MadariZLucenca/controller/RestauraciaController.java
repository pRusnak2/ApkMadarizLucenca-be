package com.example.MadariZLucenca.controller;

import com.example.MadariZLucenca.persistence.RestauraciaEntity;
import com.example.MadariZLucenca.persistence.RestauraciaRepository;
import com.example.MadariZLucenca.service.Restauracia;
import com.example.MadariZLucenca.service.RestauraciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class RestauraciaController {

    @Autowired
    private RestauraciaRepository restauraciaRepository;

    // vlozenie natvrdo. Bude sluzit na registraciu zakaznika
    @PostMapping("/test/restauracia")                          // treba volat cez postman + openvpn (profil umb). Vysledok sa ulozi do tabulky skuska.
    public String addTestData() {
        RestauraciaEntity zakaznik1 = new RestauraciaEntity();
        zakaznik1.setNazov("kebabujula");
        zakaznik1.setTyp("fasfood");
        zakaznik1.setTel_cislo(522342343);
        zakaznik1.setHeslo("halabala");
        zakaznik1.setUlica("mizerna");
        zakaznik1.setMesto("bardejov");
        zakaznik1.setPsc(4544);

        restauraciaRepository.save(zakaznik1);

        return "Hodnoty boli uložené.";
    }


    @GetMapping("/test/restauracia/{id}")       //treba cez get http://localhost:8080/test/restauracia/1
    public ResponseEntity<RestauraciaEntity> getRestauraciaById(@PathVariable Long id) {
        Optional<RestauraciaEntity> restauraciaOptional = restauraciaRepository.findById(id);
        if (restauraciaOptional.isPresent()) {
            RestauraciaEntity restauracia = restauraciaOptional.get();
            return ResponseEntity.ok().body(restauracia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private RestauraciaService restauraciaService;
    @PostMapping("/restauracia")
    public Long vytvorNovuRestauraciu(@RequestBody Restauracia restauracia) {
        return restauraciaService.vytvorNovuRestauraciu(restauracia);
    }

    @GetMapping("/restauracia/{restauraciaId}")
    public Restauracia restauraciaPodlaId(@PathVariable Long restauraciaId) {
        return restauraciaService.restauraciaPodlaId(restauraciaId);
    }



}
