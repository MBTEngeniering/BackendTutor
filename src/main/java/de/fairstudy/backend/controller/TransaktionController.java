package de.fairstudy.backend.controller;

import de.fairstudy.backend.entity.Transaktion;
import de.fairstudy.backend.service.TransaktionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/transaktionen")
public class TransaktionController {

    private final TransaktionService service;

    public TransaktionController(TransaktionService service) {
        this.service = service;
    }

    @PostMapping
    public Transaktion erstelleTransaktion(@RequestBody Transaktion transaktion) {
        // Setze Transaktionsdatum automatisch, falls nicht gesetzt
        if (transaktion.getTransaktionDatum() == null) {
            transaktion.setTransaktionDatum(java.time.LocalDateTime.now());
        }
        return service.speichereTransaktion(transaktion);
    }

    @GetMapping
    public List<Transaktion> alleTransaktionen() {
        return service.findeAlleTransaktionen();
    }

    @GetMapping("/{id}")
    public Transaktion transaktionNachId(@PathVariable UUID id) {
        return service.findeTransaktionNachId(id);
    }
    @GetMapping("/person/{personId}")
    public List<Transaktion> transaktionenNachPersonId(@PathVariable UUID personId) {
        return service.findeTransaktionenNachPersonId(personId);
    }
}
