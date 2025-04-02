package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.LoginRequestDto;
import com.onlinenachhilfe.backend.dto.TutorDto;
import com.onlinenachhilfe.backend.exception.InvalidCredentialsException;
import com.onlinenachhilfe.backend.model.Tutor;
import com.onlinenachhilfe.backend.service.TutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tutors")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping
    public TutorDto createTutor(@RequestBody TutorDto tutorDto) {
        return tutorService.createTutor(tutorDto);
    }


    @GetMapping
    public List<TutorDto> getAllTutors() {
        return tutorService.getAllTutors();
    }
    @PutMapping("/{id}")
    public TutorDto updateTutor(@PathVariable Long id, @RequestBody TutorDto tutorDto) {
        return tutorService.updateTutor(id, tutorDto); // Update method added
    }
    @GetMapping("/{id}")
    public Optional<TutorDto> getTutorById(@PathVariable Long id) {
        return tutorService.getTutorById(id);
    }

    @GetMapping("/email/{email}")
    public Optional<TutorDto> getTutorByEmail(@PathVariable String email) {
        return tutorService.getTutorByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteTutor(@PathVariable Long id) {
        tutorService.deleteTutorById(id);
    }

    @DeleteMapping("/email/{email}")
    public void deleteTutorByEmail(@PathVariable String email) {
        tutorService.deleteTutorByEmail(email);
    }

    @DeleteMapping("/phone/{phoneNumber}")
    public void deleteTutorByPhoneNumber(@PathVariable String phoneNumber) {
        tutorService.deleteTutorByPhoneNumber(phoneNumber);
    }
    @GetMapping("/lastname/{lastName}")
    public List<TutorDto> getTutorsByLastName(@PathVariable String lastName) {
        return tutorService.getTutorsByLastName(lastName);
    }

    @GetMapping("/firstname/{firstName}")
    public List<TutorDto> getTutorsByFirstName(@PathVariable String firstName) {
        return tutorService.getTutorsByFirstName(firstName);
    }

    @GetMapping("/address")
    public List<TutorDto> getTutorsByAddress(@RequestParam String street, @RequestParam String houseNumber, @RequestParam String postalCode) {
        return tutorService.getTutorsByAddress(street, houseNumber, postalCode);
    }

    @GetMapping("/phone/{phoneNumber}")
    public Optional<TutorDto> getTutorByPhoneNumber(@PathVariable String phoneNumber) {
        return tutorService.getTutorByPhoneNumber(phoneNumber);
    }

    @GetMapping("/dob/{dateOfBirth}")
    public List<TutorDto> getTutorsByDateOfBirth(@PathVariable LocalDate dateOfBirth) {
        return tutorService.getTutorsByDateOfBirth(dateOfBirth);
    }

    @GetMapping("/iban/{iban}")
    public Optional<TutorDto> getTutorByIban(@PathVariable String iban) {
        return tutorService.getTutorByIban(iban);
    }

    @GetMapping("/taxid/{taxId}")
    public List<TutorDto> getTutorsByTaxId(@PathVariable String taxId) {
        return tutorService.getTutorsByTaxId(taxId);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            Tutor student = tutorService.login(loginRequest.getEmailOrPhoneNumber(), loginRequest.getPassword());
            return ResponseEntity.ok(student);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
