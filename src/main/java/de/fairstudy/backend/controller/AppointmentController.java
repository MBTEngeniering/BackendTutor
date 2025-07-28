package de.fairstudy.backend.controller;
 
import de.fairstudy.backend.dto.AppointmentDTO;
import de.fairstudy.backend.entity.Appointment;
import de.fairstudy.backend.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable UUID id) {
        Appointment appointment = appointmentService.findById(id);
        if (appointment != null) {
            return ResponseEntity.ok(appointment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByTutorId(@PathVariable UUID tutorId) {
        List<Appointment> appointments = appointmentService.findByTutorId(tutorId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByStudentId(@PathVariable UUID studentId) {
        List<Appointment> appointments = appointmentService.findByStudentId(studentId);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable UUID id, @RequestBody AppointmentDTO appointmentDTO) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDTO);
            return ResponseEntity.ok(updatedAppointment);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable UUID id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}/student-present")
    public ResponseEntity<Appointment> updateStudentIsPresent(
            @PathVariable UUID id,
            @RequestParam boolean isPresent) {
        try {
            Appointment updated = appointmentService.updateStudentIsPresent(id, isPresent);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}/tutor-present")
    public ResponseEntity<Appointment> updateTutorIsPresent(
            @PathVariable UUID id,
            @RequestParam boolean isPresent) {
        try {
            Appointment updated = appointmentService.updateTutorIsPresent(id, isPresent);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}/student-feedback")
    public ResponseEntity<Appointment> updateStudentFeedback(
            @PathVariable UUID id,
            @RequestParam String studentFeedback) {
        try {
            Appointment updated = appointmentService.updateStudentFeedback(id, studentFeedback);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}/tutor-feedback")
    public ResponseEntity<Appointment> updateTutorFeedback(
            @PathVariable UUID id,
            @RequestParam String tutorFeedback) {
        try {
            Appointment updated = appointmentService.updateTutorFeedback(id, tutorFeedback);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
