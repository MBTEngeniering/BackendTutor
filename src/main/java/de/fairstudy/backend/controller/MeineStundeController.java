package de.fairstudy.backend.controller;

import de.fairstudy.backend.entity.MeineStunde;
import de.fairstudy.backend.service.MeineStundeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/meineStunde")
@RequiredArgsConstructor
public class MeineStundeController {

    private final MeineStundeService service;

    @PostMapping
    public ResponseEntity<MeineStunde> create(@RequestBody MeineStunde meineStunde) {
        return ResponseEntity.ok(service.create(meineStunde));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeineStunde> getById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MeineStunde>> getByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeineStunde> update(@PathVariable UUID id, @RequestBody MeineStunde updated) {
        return service.findById(id)
                .map(existing -> {
                    updated.setId(existing.getId());
                    return ResponseEntity.ok(service.update(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
