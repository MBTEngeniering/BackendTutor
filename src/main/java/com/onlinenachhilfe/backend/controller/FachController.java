package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.FachDto;
import com.onlinenachhilfe.backend.model.Fach;
import com.onlinenachhilfe.backend.model.Subject;
import com.onlinenachhilfe.backend.service.FachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fach")
public class FachController {

    private final FachService fachService;

    @Autowired
    public FachController(FachService fachService) {
        this.fachService = fachService;
    }


    private Fach convertDtoToEntity(FachDto fachDto) {
        Fach fach = new Fach();
        fach.setId(fachDto.getId());
        fach.setFach(fachDto.getFach());
        return fach;
    }

    // Methode zur Konvertierung von Fach in FachDto
    private FachDto convertEntityToDto(Fach fach) {
        FachDto fachDto = new FachDto();
        fachDto.setId(fach.getId());
       fachDto.setFach(fach.getFach());
        return fachDto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FachDto> getFachById(@PathVariable Long id) {
        Optional<Fach> fach = fachService.findFachById(id);
        return fach.map(f -> ResponseEntity.ok(convertEntityToDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/search")
    public ResponseEntity<FachDto> getFachByFach(@RequestParam Subject fach) {
        Optional<Fach> fachEntity = fachService.findFachByFach(fach);
        return fachEntity.map(f -> ResponseEntity.ok(convertEntityToDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<FachDto> createFach(@RequestBody FachDto fachDto) {
        Fach fach = convertDtoToEntity(fachDto); // DTO -> Entity
        Fach createdFach = fachService.createFach(fach.getFach());
        return ResponseEntity.ok(convertEntityToDto(createdFach)); // Entity -> DTO
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFachById(@PathVariable Long id) {
        fachService.deleteFachById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFachByFach(@RequestParam Subject fach) {
        fachService.deleteFachByFach(fach);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<FachDto> updateFach(@PathVariable Long id, @RequestBody Subject newFach) {
        try {
            Fach updatedFach = fachService.updateFach(id, newFach);
            return ResponseEntity.ok(convertEntityToDto(updatedFach));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FachDto>> getAllFaecher() {
        List<Fach> faecher = fachService.getAllFaecher();
        List<FachDto> fachDtos = faecher.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(fachDtos);
    }

}
