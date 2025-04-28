package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.LoginRequestDto;
import com.onlinenachhilfe.backend.dto.AllUserDto;
import com.onlinenachhilfe.backend.exception.InvalidCredentialsException;
import com.onlinenachhilfe.backend.model.AllUSer;
import com.onlinenachhilfe.backend.service.AllUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/allusers")
public class AllUserController {

    private final AllUserService allUserService;

    public AllUserController(AllUserService allUserService) {
        this.allUserService = allUserService;
    }

    @PostMapping
    public AllUserDto createTutor(@RequestBody AllUserDto tutorDto) {
        return allUserService.createUsers(tutorDto);
    }


    @GetMapping
    public List<AllUserDto> getAllTutors() {
        return allUserService.getAllUsers();
    }
    @PutMapping("/{id}")
    public AllUserDto updateTutor(@PathVariable UUID id, @RequestBody AllUserDto tutorDto) {
        return allUserService.updateUsers(id, tutorDto); // Update method added
    }
    @GetMapping("/{id}")
    public Optional<AllUserDto> getTutorById(@PathVariable UUID id) {
        return allUserService.getUsersById(id);
    }

    @GetMapping("/email/{email}")
    public Optional<AllUserDto> getTutorByEmail(@PathVariable String email) {
        return allUserService.getUsersByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteTutor(@PathVariable UUID id) {
        allUserService.deleteUsersById(id);
    }

    @DeleteMapping("/email/{email}")
    public void deleteTutorByEmail(@PathVariable String email) {
        allUserService.deleteUsersByEmail(email);
    }

    @DeleteMapping("/phone/{phoneNumber}")
    public void deleteTutorByPhoneNumber(@PathVariable String phoneNumber) {
        allUserService.deleteUsersByPhoneNumber(phoneNumber);
    }
    @GetMapping("/lastname/{lastName}")
    public List<AllUserDto> getTutorsByLastName(@PathVariable String lastName) {
        return allUserService.getUsersByLastName(lastName);
    }

    @GetMapping("/firstname/{firstName}")
    public List<AllUserDto> getTutorsByFirstName(@PathVariable String firstName) {
        return allUserService.getUserByFirstName(firstName);
    }

    @GetMapping("/address")
    public List<AllUserDto> getTutorsByAddress(@RequestParam String street, @RequestParam String houseNumber, @RequestParam String postalCode) {
        return allUserService.getUserByAddress(street, houseNumber, postalCode);
    }

    @GetMapping("/phone/{phoneNumber}")
    public Optional<AllUserDto> getTutorByPhoneNumber(@PathVariable String phoneNumber) {
        return allUserService.getUsersByPhoneNumber(phoneNumber);
    }

    @GetMapping("/dob/{dateOfBirth}")
    public List<AllUserDto> getTutorsByDateOfBirth(@PathVariable LocalDate dateOfBirth) {
        return allUserService.getUserByDateOfBirth(dateOfBirth);
    }

    @GetMapping("/iban/{iban}")
    public Optional<AllUserDto> getTutorByIban(@PathVariable String iban) {
        return allUserService.getUserByIban(iban);
    }

    @GetMapping("/taxid/{taxId}")
    public List<AllUserDto> getTutorsByTaxId(@PathVariable String taxId) {
        return allUserService.getUserByTaxId(taxId);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            AllUSer student = allUserService.login(loginRequest.getEmailOrPhoneNumber(), loginRequest.getPassword());
            return ResponseEntity.ok(student);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    @PostMapping("/batch-safe")
    public ResponseEntity<List<AllUserDto>> createTutorsIgnoreErrors(@RequestBody List<AllUserDto> tutorDtoList) {
        List<AllUserDto> createdTutors = allUserService.createUsersIgnoreErrors(tutorDtoList);
        return ResponseEntity.ok(createdTutors);
    }
}
