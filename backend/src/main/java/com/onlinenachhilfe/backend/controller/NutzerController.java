package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.model.Nutzer;
import com.onlinenachhilfe.backend.service.NutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nutzer")
@CrossOrigin()
public class NutzerController {

    @Autowired
    private NutzerService nutzerService;


    @PostMapping("/create")
    public ResponseEntity<Nutzer> createNutzer(@RequestBody Nutzer nutzer) {
        Nutzer savedNutzer = nutzerService.saveNutzer(nutzer);
        return ResponseEntity.ok(savedNutzer);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Nutzer> getNutzerById(@PathVariable Long id) {
        Optional<Nutzer> nutzer = nutzerService.findNutzerById(id);
        return nutzer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/all")
    public ResponseEntity<List<Nutzer>> getAllNutzer() {
        List<Nutzer> nutzerList = nutzerService.findAllNutzer();
        return ResponseEntity.ok(nutzerList);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Nutzer> updateNutzer(@PathVariable Long id, @RequestBody Nutzer nutzer) {
        try {
            Nutzer updatedNutzer = nutzerService.updateNutzer(id, nutzer);
            return ResponseEntity.ok(updatedNutzer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNutzer(@PathVariable Long id) {
        nutzerService.deleteNutzerById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search/nachname/{nachname}")
    public ResponseEntity<List<Nutzer>> searchNutzerByNachname(@PathVariable String nachname) {
        List<Nutzer> nutzerList = nutzerService.findNutzerByNachname(nachname);
        return ResponseEntity.ok(nutzerList);
    }


    @GetMapping("/search/vorname/{vorname}")
    public ResponseEntity<List<Nutzer>> searchNutzerByVorname(@PathVariable String vorname) {
        List<Nutzer> nutzerList = nutzerService.findNutzerByVorname(vorname);
        return ResponseEntity.ok(nutzerList);
    }


    @GetMapping("/search/email/{emailadresse}")
    public ResponseEntity<Nutzer> searchNutzerByEmail(@PathVariable String emailadresse) {
        Optional<Nutzer> nutzer = nutzerService.findNutzerByEmail(emailadresse);
        return nutzer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/search/telefon/{telefonnummer}")
    public ResponseEntity<Nutzer> searchNutzerByTelefonnummer(@PathVariable String telefonnummer) {
        Optional<Nutzer> nutzer = nutzerService.findNutzerByTelefonnummer(telefonnummer);
        return nutzer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/search/adresse")
    public ResponseEntity<List<Nutzer>> searchNutzerByAdresse(
            @RequestParam String hausnummer,
            @RequestParam String strasse,
            @RequestParam String land,
            @RequestParam String postleitzahl,
            @RequestParam String stadt) {
        List<Nutzer> nutzerList = nutzerService.findNutzerByAdresse(hausnummer, strasse, land, postleitzahl, stadt);
        return ResponseEntity.ok(nutzerList);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String identifiant, @RequestParam String passwort) {

        Optional<Nutzer> optionalNutzer = nutzerService.findAllNutzer().stream()
                .filter(nutzer ->
                        (identifiant.equalsIgnoreCase(nutzer.getEmailadresse()) ||
                                identifiant.equalsIgnoreCase(nutzer.getTelefonnummer())) &&
                                passwort.equals(nutzer.getPasswort()))
                .findFirst();


        if (optionalNutzer.isPresent()) {
            return ResponseEntity.ok(optionalNutzer.get().getVorname() + " !");
        }

        // Si les informations sont incorrectes
        return ResponseEntity.status(401).body(" Einlogen  Daten stimmen nicht");
    }
}
