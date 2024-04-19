package com.example.MadariZLucenca.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AlgJedlo")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AlgJedloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long foodId;
    private Long allergenId;


    @ManyToMany
    @JoinTable(name = "AlgJedlo_Alergeny",
            joinColumns = @JoinColumn(name = "algjedlo_id"),
            inverseJoinColumns = @JoinColumn(name = "alergeny_id"))
    private Set<AlergenyEntity> alergenyEntities = new HashSet<>();
}
