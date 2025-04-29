package de.fairstudy.backend.controller;

import de.fairstudy.backend.auth.AuthenticationRequest;
import de.fairstudy.backend.auth.AuthenticationResponse;
import de.fairstudy.backend.event.UserLoginEvent;
import de.fairstudy.backend.service.api.ApiAuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /*private final ApiAuthenticationService apiAuthenticationService;

    private final ApplicationEventPublisher eventPublisher;

    public AuthController(ApiAuthenticationService apiAuthenticationService, ApplicationEventPublisher eventPublisher) {
        this.apiAuthenticationService = apiAuthenticationService;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping(value = {"/authenticate", "/login"})
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = apiAuthenticationService.authenticate(request);
            eventPublisher.publishEvent(new UserLoginEvent(response.getUsername()));
            return ResponseEntity.ok(response);
        } catch (ExpiredJwtException bce) {
            AuthenticationResponse exp = AuthenticationResponse.builder().token("Token expired").build();
            return ResponseEntity.ok(exp);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Login failed: " + ex.getMessage());
        }
    }*/
}
