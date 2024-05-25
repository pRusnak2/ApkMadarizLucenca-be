package com.example.MadariZLucenca.persistence;

import com.example.MadariZLucenca.service.Customer;
import com.example.MadariZLucenca.service.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Entity(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    public String getRoleName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
