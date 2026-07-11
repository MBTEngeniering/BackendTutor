package de.fairstudy.backend.controller;

import de.fairstudy.backend.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final MessageService messageService;

    public PublicController(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping("/ping")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/message")
    public String message() {
        return messageService.getMessage();
    }
}
