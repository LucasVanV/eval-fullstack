package org.example.vanvooren.controller;

import org.example.vanvooren.model.Absence;
import org.example.vanvooren.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @GetMapping
    public List<Absence> getAll() {
        return absenceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Absence> getById(@PathVariable Long id) {
        return absenceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Absence create(@RequestBody Absence absence) {
        return absenceService.save(absence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Absence> update(@PathVariable Long id, @RequestBody Absence absence) {
        return absenceService.update(id, absence)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        absenceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
