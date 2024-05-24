package com.example.MadariZLucenca.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    AdminEntity admin;


    @Column(name = "valid_until", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

}

