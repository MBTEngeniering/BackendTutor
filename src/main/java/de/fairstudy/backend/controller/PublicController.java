package de.fairstudy.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/ping")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("OK");
    }
}
