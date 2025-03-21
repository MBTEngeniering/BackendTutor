package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.VertragDto;
import com.onlinenachhilfe.backend.model.Vertrag;
import com.onlinenachhilfe.backend.service.VertragService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vertrag")
public class VertragController {

    @Autowired
    private VertragService vertragService;

    @PostMapping("/create")
    public ResponseEntity<Vertrag> createVertrag(@RequestBody VertragDto vertragDto) {
        Vertrag vertrag = vertragService.saveVertrag(vertragDto);
        return ResponseEntity.ok(vertrag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vertrag> getVertragById(@PathVariable Long id) {
        Optional<Vertrag> vertrag = vertragService.findVertragById(id);
        return vertrag.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vertrag>> getAllVertraege() {
        List<Vertrag> vertraege = vertragService.findAllVertraege();
        return ResponseEntity.ok(vertraege);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Vertrag> updateVertrag(@PathVariable Long id, @RequestBody VertragDto vertragDto) {
        try {
            Vertrag updatedVertrag = vertragService.updateVertrag(id, vertragDto);
            return ResponseEntity.ok(updatedVertrag);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVertrag(@PathVariable Long id) {
        vertragService.deleteVertragById(id);
        return ResponseEntity.noContent().build();
    }
}
