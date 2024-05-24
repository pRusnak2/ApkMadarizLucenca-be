package com.example.MadariZLucenca.persistence;

import com.example.MadariZLucenca.service.Customer;
import com.example.MadariZLucenca.service.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER) // Eager fetching ensures roles are loaded with Login
    private Set<RoleEntity> roles;

    @OneToOne
    private CustomerEntity customer;

    @OneToOne
    private RestaurantEntity restaurant;

    @OneToOne
    private AdminEntity admin;
}