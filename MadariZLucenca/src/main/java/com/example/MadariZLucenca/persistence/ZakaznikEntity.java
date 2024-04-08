package com.example.MadariZLucenca.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Zakaznici")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZakaznikEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_zakaznika;

    private String meno;

    private int tel_cislo;

    private String email;

    private String heslo;

    private String ulica;

    private String mesto;

    private int psc;



}
