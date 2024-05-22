package com.example.MadariZLucenca.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Zakaznici")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private int telNumber;

    private String email;

    private String username;

    private String password;

    private String streetName;

    private String cityName;

    private int postCode;

    private String firstName;

    private String lastName;

    private String passwordHash;


    @OneToMany
    private Set<OrderEntity> orders;

    private String roleName;
}
