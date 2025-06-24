package org.example.vanvooren.service;

import org.example.vanvooren.model.Conges;
import org.example.vanvooren.repository.CongesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongesService {
    @Autowired
    private CongesRepository congesRepository;

    public List<Conges> findAll() {
        return congesRepository.findAll();
    }

    public Optional<Conges> findById(Long id) {
        return congesRepository.findById(id);
    }

    public Conges save(Conges conges) {
        return congesRepository.save(conges);
    }

    public void deleteById(Long id) {
        congesRepository.deleteById(id);
    }

    public Optional<Conges> update(Long id, Conges congesDetails) {
        return congesRepository.findById(id).map(conges -> {
            conges.setDateDebut(congesDetails.getDateDebut());
            conges.setDateFin(congesDetails.getDateFin());
            return congesRepository.save(conges);
        });
    }
}
