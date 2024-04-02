package com.example.MadariZLucenca.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restauracia {

    private Long id_restauracie;
    private String nazov;
    private String typ;
    private int tel_cislo;
    private String heslo;
    private String ulica;
    private String mesto;
    private int psc;
}
