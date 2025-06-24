package org.example.vanvooren.controller;

import org.example.vanvooren.model.Candidat;
import org.example.vanvooren.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/candidats")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    @GetMapping
    public List<Candidat> getAll() {
        return candidatService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidat> getById(@PathVariable Long id) {
        return candidatService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Candidat create(@RequestBody @Valid Candidat candidat) {
        return candidatService.save(candidat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidat> update(@PathVariable Long id, @RequestBody @Valid Candidat candidat) {
        return candidatService.update(id, candidat)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        candidatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
