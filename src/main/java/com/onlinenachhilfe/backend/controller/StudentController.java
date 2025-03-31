package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.LoginRequestDto;
import com.onlinenachhilfe.backend.dto.StudentDto;
import com.onlinenachhilfe.backend.exception.InvalidCredentialsException;
import com.onlinenachhilfe.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.createStudent(studentDto));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDto> getStudentByEmail(@PathVariable String email) {
        return studentService.getStudentByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<StudentDto>> getStudentsByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(studentService.getStudentsByLastName(lastName));
    }

    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<StudentDto>> getStudentsByFirstName(@PathVariable String firstName) {
        return ResponseEntity.ok(studentService.getStudentsByFirstName(firstName));
    }

    @GetMapping("/address")
    public ResponseEntity<List<StudentDto>> getStudentsByAddress(@RequestParam String street, @RequestParam String houseNumber, @RequestParam String postalCode) {
        return ResponseEntity.ok(studentService.getStudentsByAddress(street, houseNumber, postalCode));
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<StudentDto> getStudentByPhoneNumber(@PathVariable String phoneNumber) {
        return studentService.getStudentByPhoneNumber(phoneNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dob/{dateOfBirth}")
    public ResponseEntity<List<StudentDto>> getStudentsByDateOfBirth(@PathVariable LocalDate dateOfBirth) {
        return ResponseEntity.ok(studentService.getStudentsByDateOfBirth(dateOfBirth));
    }

    @GetMapping("/iban/{iban}")
    public ResponseEntity<StudentDto> getStudentByIban(@PathVariable String iban) {
        return studentService.getStudentByIban(iban)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/parent/{parentFirstName}")
    public ResponseEntity<List<StudentDto>> getStudentsByParentFirstName(@PathVariable String parentFirstName) {
        return ResponseEntity.ok(studentService.getStudentsByParentFirstName(parentFirstName));
    }
    @PutMapping("/{id}")
    public StudentDto updateTutor(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(id, studentDto);
    }
    @DeleteMapping("/email/{email}")
    public void deleteStudentByEmail(@PathVariable String email) {
        studentService.deleteStudentByEmail(email);
    }

    @DeleteMapping("/phone/{phoneNumber}")
    public void deleteStudentByPhoneNumber(@PathVariable String phoneNumber) {
        studentService.deleteStudentByPhoneNumber(phoneNumber);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            String result = studentService.login(loginRequest.getEmailOrPhoneNumber(), loginRequest.getPassword());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
