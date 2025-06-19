package de.fairstudy.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.fairstudy.backend.auth.AuthenticationResponse;
import de.fairstudy.backend.dto.AllUserDto;
import de.fairstudy.backend.dto.LoginRequestDto;
import de.fairstudy.backend.entity.AllUser;
import de.fairstudy.backend.event.UserLoginEvent;
import de.fairstudy.backend.exception.InvalidCredentialsException;
import de.fairstudy.backend.service.AllUserService;
import de.fairstudy.backend.service.api.ApiAuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ApiAuthenticationService apiAuthenticationService;

    private final ApplicationEventPublisher eventPublisher;

    private final AllUserService allUserService;

    public AuthController(ApiAuthenticationService apiAuthenticationService, ApplicationEventPublisher eventPublisher, AllUserService allUserService) {
        this.apiAuthenticationService = apiAuthenticationService;
        this.eventPublisher = eventPublisher;
        this.allUserService = allUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createTutor(@RequestBody AllUserDto tutorDto) {
        try {
            allUserService.createUsers(tutorDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            AllUser student = allUserService.login(loginRequest.getEmailOrPhoneNumber(), loginRequest.getPassword());
            return ResponseEntity.ok(student);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping(value = {"/authenticate"})
    public ResponseEntity<?> authenticate(@RequestBody LoginRequestDto request) {
        try {
            var response = apiAuthenticationService.authenticate(request);
            eventPublisher.publishEvent(new UserLoginEvent(response.getUsername()));

            var user = response.getUser();
            user.setPassword(null);
            user.setTimestamp(response.getTimestamp() / 1000L); // Convert to seconds
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(user);

            // Return cookie in response
            return ResponseEntity.ok()
                    .header("Set-Cookie", response.getCookie())
                    .body(json);
        } catch (ExpiredJwtException bce) {
            AuthenticationResponse exp = AuthenticationResponse.builder().token("Token expired").build();
            return ResponseEntity.badRequest().body(exp);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Login failed: " + ex.getMessage());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            var response = apiAuthenticationService.logout();
            return ResponseEntity.ok()
                    .header("Set-Cookie", response.getCookie())
                    .body("Logout successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
