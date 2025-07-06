package de.fairstudy.backend.controller;

import de.fairstudy.backend.entity.VerdienTabelle;
import de.fairstudy.backend.service.VerdienTabelleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/verdientabelle")
public class VerdienTabelleController {

    private final VerdienTabelleService service;

    public VerdienTabelleController(VerdienTabelleService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public VerdienTabelle createVerdienTabelle(@RequestParam UUID tutorId,
                                               @RequestParam UUID appointmentId) {
        return service.createVerdienTabelle(tutorId, appointmentId);
    }

    @GetMapping("/tutor/{tutorId}")
    public List<VerdienTabelle> getVerdienByAppointementId(@PathVariable UUID tutorId) {
        return service.getVerdienByAppointementId(tutorId);
    }
}
