package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.TerminDto;
import com.onlinenachhilfe.backend.model.Termin;
import com.onlinenachhilfe.backend.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/termin")
public class TerminController {

    @Autowired
    private TerminService terminService;

    @PostMapping("/create")
    public ResponseEntity<Termin> createTermin(@RequestBody TerminDto terminDto) {
        Termin termin = terminService.saveTermin(terminDto);
        return ResponseEntity.ok(termin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Termin> getTerminById(@PathVariable Long id) {
        Optional<Termin> termin = terminService.findTerminById(id);
        return termin.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Termin>> getAllTermine() {
        List<Termin> termine = terminService.findAllTermine();
        return ResponseEntity.ok(termine);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Termin> updateTermin(@PathVariable Long id, @RequestBody TerminDto terminDto) {
        try {
            Termin updatedTermin = terminService.updateTermin(id, terminDto);
            return ResponseEntity.ok(updatedTermin);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTermin(@PathVariable Long id) {
        terminService.deleteTerminById(id);
        return ResponseEntity.noContent().build();
    }
}
