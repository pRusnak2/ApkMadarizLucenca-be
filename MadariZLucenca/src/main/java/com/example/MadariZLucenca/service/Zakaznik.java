package com.example.MadariZLucenca.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zakaznik {

    private Long id_zakaznika;
    private int tel_cislo;
    private String email;
    private String heslo;
    private String ulica;
    private String mesto;
    private int psc;
}
