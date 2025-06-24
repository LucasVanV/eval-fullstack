package org.example.vanvooren.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employe {
    @Id @GeneratedValue
    private Long id;

    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String occupation;

    @NotNull
    private Double salaire;

    @NotNull
    private LocalDate debutContrat;

    private LocalDate finContrat;

    @NotNull @NotBlank
    private String numeroIdentification;

    @NotNull
    private LocalDate dateNaissance;

    @NotNull @NotBlank
    private String adresse;

    @NotNull @NotBlank
    @Email
    private String email;

    @NotNull @NotBlank
    private String telephone;

    private String observation;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Absence> absences;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Conges> conges;
}
