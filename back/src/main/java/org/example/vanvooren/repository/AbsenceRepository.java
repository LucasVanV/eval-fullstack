package org.example.vanvooren.repository;

import org.example.vanvooren.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence, Long> { 
}
