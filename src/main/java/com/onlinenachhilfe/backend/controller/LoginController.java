package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.LoginRequestDto;
import com.onlinenachhilfe.backend.service.StudentService;
import com.onlinenachhilfe.backend.service.TutorService;
import com.onlinenachhilfe.backend.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TutorService tutorService;

    private String loginStudent(String emailOrPhoneNumber, String password) throws InvalidCredentialsException {
        return studentService.login(emailOrPhoneNumber, password);
    }

    private String loginTutor(String emailOrPhoneNumber, String password) throws InvalidCredentialsException {
        return tutorService.login(emailOrPhoneNumber, password);
    }


    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequest) {
        try {

            try {
                String studentResult = loginStudent(loginRequest.getEmailOrPhoneNumber(), loginRequest.getPassword());
                return new ResponseEntity<>(studentResult, HttpStatus.OK);
            } catch (InvalidCredentialsException studentException) {
                try {
                    String tutorResult = loginTutor(loginRequest.getEmailOrPhoneNumber(), loginRequest.getPassword());
                    return new ResponseEntity<>(tutorResult, HttpStatus.OK);
                } catch (InvalidCredentialsException tutorException) {
                    return new ResponseEntity<>("Ungültige Anmeldedaten für Student und Tutor", HttpStatus.UNAUTHORIZED);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Fehler bei der Anmeldung: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
