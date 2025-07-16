package de.fairstudy.backend.controller;

import de.fairstudy.backend.entity.VerdienTabelle;
import de.fairstudy.backend.service.VerdienTabelleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/verdientabelle")
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

    @GetMapping("/appointement/{appointementId}")
    public List<VerdienTabelle> getVerdienByAppointementId(@PathVariable UUID appointementId) {
        return service.getVerdienByAppointementId(appointementId);
    }
    @GetMapping("/tutor/{tutorId}")
    public List<VerdienTabelle> getVerdienBytutorId(@PathVariable UUID tutorId) {
        return service.getVerdienByTutorId(tutorId);
    }
    @GetMapping("/student/{studentId}")
    public List<VerdienTabelle> getVerdienBystudentId(@PathVariable UUID studentId) {
        return service.getVerdienByStudentId(studentId);
    }
}
