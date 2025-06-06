package de.fairstudy.backend.controller;

import de.fairstudy.backend.dto.AvailabilityDto;
import de.fairstudy.backend.entity.Availability;
import de.fairstudy.backend.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @PostMapping("/availability/save")
    public ResponseEntity<?> create(@RequestBody AvailabilityDto dto) {
        if (availabilityService.existsByAllFields(dto)) {
            return ResponseEntity.status(409).body("Availability already exists");
        }
        return ResponseEntity.ok(availabilityService.createAvailability(dto));
    }

    @GetMapping("/availability/user/{userId}")
    public ResponseEntity<List<AvailabilityDto>> getByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(availabilityService.getAllByUserId(userId));
    }

    @GetMapping("/availability")
    public ResponseEntity<List<Availability>> getAll() {
        return ResponseEntity.ok(availabilityService.getAll());
    }

    @DeleteMapping("/availability/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/availability/deleteByFields")
    public ResponseEntity<Void> deleteByAllFields(@RequestBody AvailabilityDto dto) {
        availabilityService.deleteByAllFields(dto);
        return ResponseEntity.noContent().build();
    }
}
