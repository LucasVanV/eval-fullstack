package org.example.vanvooren.service;

import org.example.vanvooren.model.Absence;
import org.example.vanvooren.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {
    @Autowired
    private AbsenceRepository absenceRepository;

    public List<Absence> findAll() {
        return absenceRepository.findAll();
    }

    public Optional<Absence> findById(Long id) {
        return absenceRepository.findById(id);
    }

    public Absence save(Absence absence) {
        return absenceRepository.save(absence);
    }

    public void deleteById(Long id) {
        absenceRepository.deleteById(id);
    }

    public Optional<Absence> update(Long id, Absence absenceDetails) {
        return absenceRepository.findById(id).map(absence -> {
            absence.setJour(absenceDetails.getJour());
            return absenceRepository.save(absence);
        });
    }
}
