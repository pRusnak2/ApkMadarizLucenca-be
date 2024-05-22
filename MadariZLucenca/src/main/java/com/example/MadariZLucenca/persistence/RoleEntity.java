package com.example.MadariZLucenca.persistence;

import com.example.MadariZLucenca.service.Customer;
import com.example.MadariZLucenca.service.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Role;

import java.util.Collection;

@Entity(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "role")
    private CustomerEntity customer;

    @OneToOne(mappedBy = "role")
    private RestaurantEntity restaurant;

    public Object getRoleName() {
        return name;
    }
}
