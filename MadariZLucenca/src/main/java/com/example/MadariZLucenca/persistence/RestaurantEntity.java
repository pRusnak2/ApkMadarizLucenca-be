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
@Table(name = "Restauracie")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    private String name;

    private String type;

    private int telNumber;

    private String username;

    private String password;

    private String streetName;

    private String cityName;

    private int postCode;
}
