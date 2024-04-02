package com.example.MadariZLucenca.service;

import com.example.MadariZLucenca.persistence.RestauraciaEntity;
import com.example.MadariZLucenca.persistence.RestauraciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestauraciaService {

    @Autowired
    private RestauraciaRepository restauraciaRepository;
    public Long vytvorNovuRestauraciu(Restauracia restauracia) {
        RestauraciaEntity entity = new RestauraciaEntity();
        entity.setNazov(restauracia.getNazov());
        entity.setTyp(restauracia.getTyp());
        entity.setTel_cislo(restauracia.getTel_cislo());
        entity.setHeslo(restauracia.getHeslo());
        entity.setUlica(restauracia.getUlica());
        entity.setMesto(restauracia.getMesto());
        entity.setPsc(restauracia.getPsc());
        restauraciaRepository.save(entity);
        return entity.getId_restauracie();
    }


    public Restauracia restauraciaPodlaId (Long id){
        Optional<RestauraciaEntity> opt = restauraciaRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        RestauraciaEntity entity = opt.get();
        Restauracia restauracia = new Restauracia();
        restauracia.setId_restauracie(entity.getId_restauracie());
        restauracia.setNazov(entity.getNazov());
        restauracia.setTyp(entity.getTyp());
        restauracia.setTel_cislo(entity.getTel_cislo());
        restauracia.setHeslo(entity.getHeslo());
        restauracia.setUlica(entity.getUlica());
        restauracia.setMesto(entity.getMesto());
        return restauracia;
    }
}
