package org.example.vanvooren.controller;

import org.example.vanvooren.model.Conges;
import org.example.vanvooren.service.CongesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conges")
public class CongesController {

    @Autowired
    private CongesService congesService;

    @GetMapping
    public List<Conges> getAll() {
        return congesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conges> getById(@PathVariable Long id) {
        return congesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Conges create(@RequestBody Conges conges) {
        return congesService.save(conges);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conges> update(@PathVariable Long id, @RequestBody Conges conges) {
        return congesService.update(id, conges)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        congesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
