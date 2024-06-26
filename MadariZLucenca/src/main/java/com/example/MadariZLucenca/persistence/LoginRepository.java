package com.example.MadariZLucenca.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    Optional<LoginEntity> findByUsername(String username);
    Optional<LoginEntity> findByRestaurant(RestaurantEntity restaurant);

}
