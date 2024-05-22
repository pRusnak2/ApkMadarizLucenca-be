package com.example.MadariZLucenca.persistence;

import com.example.MadariZLucenca.service.Customer;
import com.example.MadariZLucenca.service.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Role;

import java.util.Collection;
import java.util.Set;

@Entity(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    private Long id;

    private String name;

    public String getRoleName() {
        return name;
    }
}
