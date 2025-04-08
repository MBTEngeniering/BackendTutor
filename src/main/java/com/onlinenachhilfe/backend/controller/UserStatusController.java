package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.StudentDto;
import com.onlinenachhilfe.backend.dto.TutorDto;
import com.onlinenachhilfe.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userstatus")
public class UserStatusController {

    @Autowired
    private StudentController studentController;

    @Autowired
    private TutorController tutorController;

    // Alle Studenten mit RoleId 1 (z.B. SCHUELER)
    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentController.getAllStudents().getBody();
        students.removeIf(student -> !student.getUserRoleIds().contains(1L));
        return ResponseEntity.ok(students);
    }

    // Alle Tutoren mit RoleId 2 (z.B. LEHRER)
    @GetMapping("/tutors")
    public ResponseEntity<List<TutorDto>> getAllTutors() {
        List<TutorDto> tutors = tutorController.getAllTutors();
        tutors.removeIf(tutor -> !tutor.getUserRoleIds().contains(2L));
        return ResponseEntity.ok(tutors);
    }

    // Alle Unternehmen mit RoleId 3 (z.B. ENTREPRENEUR)
    @GetMapping("/entreprise")
    public ResponseEntity<List<TutorDto>> getAllEntreprise() {
        List<TutorDto> tutors = tutorController.getAllTutors();
        tutors.removeIf(tutor -> !tutor.getUserRoleIds().contains(3L));
        return ResponseEntity.ok(tutors);
    }

    // Alle Administratoren mit RoleId 5 (z.B. ADMINISTRATOR)
    @GetMapping("/admins")
    public ResponseEntity<List<TutorDto>> getAllAdmins() {
        List<TutorDto> tutors = tutorController.getAllTutors();
        tutors.removeIf(tutor -> !tutor.getUserRoleIds().contains(5L));
        return ResponseEntity.ok(tutors);
    }

    // Alle Manager mit RoleId 6 (z.B. BEARBEITER)
    @GetMapping("/managers")
    public ResponseEntity<List<TutorDto>> getAllManagers() {
        List<TutorDto> tutors = tutorController.getAllTutors();
        tutors.removeIf(tutor -> !tutor.getUserRoleIds().contains(6L));
        return ResponseEntity.ok(tutors);
    }

    // Alle Finanzabteilungen mit RoleId 4 (z.B. FINANCE)
    @GetMapping("/finance")
    public ResponseEntity<List<TutorDto>> getAllFinance() {
        List<TutorDto> tutors = tutorController.getAllTutors();
        tutors.removeIf(tutor -> !tutor.getUserRoleIds().contains(4L));
        return ResponseEntity.ok(tutors);
    }
}
