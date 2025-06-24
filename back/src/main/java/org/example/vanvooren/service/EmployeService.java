package org.example.vanvooren.service;

import org.example.vanvooren.model.Employe;
import org.example.vanvooren.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {
    @Autowired
    private EmployeRepository employeRepository;

    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    public Optional<Employe> findById(Long id) {
        return employeRepository.findById(id);
    }

    public Employe save(Employe employe) {
        return employeRepository.save(employe);
    }

    public void deleteById(Long id) {
        employeRepository.deleteById(id);
    }

    public Optional<Employe> update(Long id, Employe employeDetails) {
        return employeRepository.findById(id).map(employe -> {
            employe.setName(employeDetails.getName());
            employe.setOccupation(employeDetails.getOccupation());
            employe.setSalaire(employeDetails.getSalaire());
            employe.setDebutContrat(employeDetails.getDebutContrat());
            employe.setFinContrat(employeDetails.getFinContrat());
            employe.setNumeroIdentification(employeDetails.getNumeroIdentification());
            employe.setDateNaissance(employeDetails.getDateNaissance());
            employe.setAdresse(employeDetails.getAdresse());
            employe.setEmail(employeDetails.getEmail());
            employe.setTelephone(employeDetails.getTelephone());
            employe.setObservation(employeDetails.getObservation());
            return employeRepository.save(employe);
        });
    }
}
