package com.example.MadariZLucenca.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Alergeny")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlergenyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allergenId;
    private String name;

}