package org.example.vanvooren.service;

import org.example.vanvooren.model.Candidat;
import org.example.vanvooren.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository candidatRepository;

    public List<Candidat> findAll() {
        return candidatRepository.findAll();
    }

    public Optional<Candidat> findById(Long id) {
        return candidatRepository.findById(id);
    }

    public Candidat save(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public void deleteById(Long id) {
        candidatRepository.deleteById(id);
    }

    public Optional<Candidat> update(Long id, Candidat candidatDetails) {
        return candidatRepository.findById(id).map(candidat -> {
            candidat.setNom(candidatDetails.getNom());
            candidat.setNumeroIdentification(candidatDetails.getNumeroIdentification());
            candidat.setAdresse(candidatDetails.getAdresse());
            candidat.setDateNaissance(candidatDetails.getDateNaissance());
            candidat.setEmail(candidatDetails.getEmail());
            candidat.setNumeroTelephone(candidatDetails.getNumeroTelephone());
            candidat.setDomaineTechnique(candidatDetails.getDomaineTechnique());
            candidat.setEvaluation(candidatDetails.getEvaluation());
            candidat.setDateEntretien(candidatDetails.getDateEntretien());
            candidat.setObservation(candidatDetails.getObservation());
            return candidatRepository.save(candidat);
        });
    }
}
