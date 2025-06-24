package org.example.vanvooren.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidat {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull @NotBlank
    private String nom;

    @NotNull @NotBlank
    private String numeroIdentification;

    @NotNull @NotBlank
    private String adresse;

    @NotNull
    private LocalDate dateNaissance;

    @NotNull @NotBlank
    @Email
    private String email;

    @NotNull @NotBlank
    private String numeroTelephone;

    @NotNull @NotBlank
    private String domaineTechnique;

    @NotNull
    private Integer evaluation;

    private LocalDate dateEntretien;

    private String observation;
}
