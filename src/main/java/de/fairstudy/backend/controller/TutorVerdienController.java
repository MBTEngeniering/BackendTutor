package de.fairstudy.backend.controller;
import de.fairstudy.backend.entity.TutorVerdien;
import de.fairstudy.backend.service.TutorVerdienService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/verdien")
public class TutorVerdienController {

    private final TutorVerdienService service;

    public TutorVerdienController(TutorVerdienService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public TutorVerdien createVerdien(@RequestParam UUID tutorId) {
        return service.createVerdien(tutorId);
    }

    @PutMapping("/update/{id}")
    public Optional<TutorVerdien> updateVerdien(@PathVariable UUID id) {
        return service.updateVerdien(id);
    }

    @GetMapping("/tutor/{tutorId}")
    public Optional<TutorVerdien> getVerdienByTutorId(@PathVariable UUID tutorId) {
        return service.getVerdienByTutorId(tutorId);
    }
}
