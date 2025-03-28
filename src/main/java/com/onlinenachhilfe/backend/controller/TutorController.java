package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.TutorDto;
import com.onlinenachhilfe.backend.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tutors")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    public TutorDto createTutor(@RequestBody TutorDto tutorDto) {
        return tutorService.createTutor(tutorDto);
    }

    @GetMapping
    public List<TutorDto> getAllTutors() {
        return tutorService.getAllTutors();
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
        tutorService.deleteTutor(id);
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
}
