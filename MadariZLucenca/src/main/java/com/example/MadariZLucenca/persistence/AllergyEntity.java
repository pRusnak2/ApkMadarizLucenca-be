package com.example.MadariZLucenca.persistence;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Alergie")
@NoArgsConstructor
@AllArgsConstructor
public class AllergyEntity {
    @Id
    private Long allergyId;

    private String allergyName;


}
