package de.fairstudy.backend.controller;

import de.fairstudy.backend.dto.FachDto;
import de.fairstudy.backend.entity.Fach;
import de.fairstudy.backend.model.enums.Subject;
import de.fairstudy.backend.service.FachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fach")
//@Secured({"ROLE_STUDENT", "ROLE_TUTOR", "ROLE_ADMIN"})
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

    @GetMapping("/trans/{lang}")
//    @Secured({"ROLE_ENTERPRISE", "ROLE_ADMIN"})
    public ResponseEntity<?> getFachByLang(@PathVariable String lang) {
        lang = lang.toUpperCase().charAt(0) + lang.substring(1).toLowerCase();

        List<Fach> faecher = fachService.getAllFaecher();
        List<FachDto> fachDtos = new ArrayList<>();
        try {
            Method method = Fach.class.getMethod("get" + lang);
            for (Fach fach : faecher) {
                FachDto fachDto = convertEntityToDto(fach);
                fachDto.setTranslation((String) method.invoke(fach));
                fachDtos.add(fachDto);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("{\"Error\": \"Language not found\"}");
        }

        return ResponseEntity.ok(fachDtos);
    }

    @GetMapping("/search")
    public ResponseEntity<FachDto> getFachByFach(@RequestParam Subject fach) {
        Optional<Fach> fachEntity = fachService.findFachByFach(fach);
        return fachEntity.map(f -> ResponseEntity.ok(convertEntityToDto(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<FachDto> createFach(@RequestBody FachDto fachDto) {
        Fach fach = convertDtoToEntity(fachDto); // DTO -> Entity
        Fach createdFach = fachService.createFach(fach.getFach());
        return ResponseEntity.ok(convertEntityToDto(createdFach)); // Entity -> DTO
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
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

    @GetMapping("/")
    public ResponseEntity<List<FachDto>> getAllFaecher() {
        List<Fach> faecher = fachService.getAllFaecher();
        List<FachDto> fachDtos = faecher.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(fachDtos);
    }
}
