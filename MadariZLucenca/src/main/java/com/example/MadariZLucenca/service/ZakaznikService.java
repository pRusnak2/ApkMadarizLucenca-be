package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.ZakaznikEntity;
import com.example.MadariZLucenca.persistence.ZakaznikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ZakaznikService {

    @Autowired
    private ZakaznikRepository zakaznikRepository;
    public Long vytvorNovehoZakaznika(Zakaznik zakaznik) {
        ZakaznikEntity entity = new ZakaznikEntity();
        entity.setMeno(zakaznik.getMeno());
        entity.setTel_cislo(zakaznik.getTel_cislo());
        entity.setEmail(zakaznik.getEmail());
        entity.setHeslo(zakaznik.getHeslo());
        entity.setUlica(zakaznik.getUlica());
        entity.setMesto(zakaznik.getMesto());
        entity.setPsc(zakaznik.getPsc());
        zakaznikRepository.save(entity);
        return entity.getId_zakaznika();
    }


    public Zakaznik zakaznikPodlaId (Long id){
        Optional<ZakaznikEntity> opt = zakaznikRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        ZakaznikEntity entity = opt.get();
        Zakaznik zakaznik = new Zakaznik();
        zakaznik.setId_zakaznika(entity.getId_zakaznika());
        zakaznik.setMeno(entity.getMeno());
        zakaznik.setTel_cislo(entity.getTel_cislo());
        zakaznik.setEmail(entity.getEmail());
        zakaznik.setHeslo(entity.getHeslo());
        zakaznik.setUlica(entity.getUlica());
        zakaznik.setMesto(entity.getMesto());
        return zakaznik;
    }
}
