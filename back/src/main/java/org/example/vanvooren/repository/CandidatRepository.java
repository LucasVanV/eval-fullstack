package org.example.vanvooren.repository;

import org.example.vanvooren.model.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat, Long> { 
}
