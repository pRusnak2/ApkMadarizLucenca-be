package com.example.MadariZLucenca.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Jedla")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;
    private String name;
    private String description;
    private float price;
    private Long restaurantId;

    @ManyToMany
    @JoinTable(
            name = "Alg_Jedlo",
            joinColumns = @JoinColumn(name = "foodId"),
            inverseJoinColumns = @JoinColumn(name = "allergenId")
    )
    private Set<AlergenyEntity> allergens;
}
