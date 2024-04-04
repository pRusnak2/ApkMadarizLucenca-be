package com.example.MadariZLucenca.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RestauraciaRepository extends JpaRepository<RestauraciaEntity, Long> {
}
