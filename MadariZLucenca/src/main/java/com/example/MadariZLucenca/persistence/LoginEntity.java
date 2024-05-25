package com.example.MadariZLucenca.persistence;

import com.example.MadariZLucenca.service.Customer;
import com.example.MadariZLucenca.service.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "Prihlasenie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String passwordHash;

    @ManyToMany(fetch = FetchType.EAGER) // Consider eager fetching for roles
    @JoinTable(name = "prihlasenie_roles",
            joinColumns = @JoinColumn(name = "prihlasenie_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))    private Set<RoleEntity> roles;

    @OneToOne
    private CustomerEntity customer;

    @OneToOne
    private RestaurantEntity restaurant;

    @OneToOne
    private AdminEntity admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}