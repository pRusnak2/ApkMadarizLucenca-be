package com.example.MadariZLucenca.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.Set;


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

    private String roleName;

    @OneToMany
    private Set<OrderEntity> orders;
}
