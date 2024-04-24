package com.example.MadariZLucenca.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MadariZLucenca.persistence.AlergenyEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergenyRepository extends JpaRepository<AlergenyEntity, Long> {
}

