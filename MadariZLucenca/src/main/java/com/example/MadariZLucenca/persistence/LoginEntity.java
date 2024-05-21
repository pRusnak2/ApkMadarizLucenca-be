package com.example.MadariZLucenca.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Prihlasenie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String passwordHash;

    @OneToOne
    @JoinColumn(name = "id")
    private RoleEntity role;

    @OneToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(name = "restaurantId")
    private RestaurantEntity restaurant;
}
