package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.EnterpriseDto;
import com.onlinenachhilfe.backend.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    // GET: Alle Unternehmen
    @GetMapping
    public ResponseEntity<List<EnterpriseDto>> getAllEnterprises() {
        List<EnterpriseDto> enterprises = enterpriseService.findAllEnterprises();
        return ResponseEntity.ok(enterprises);
    }

    // GET: Unternehmen nach ID
    @GetMapping("/{id}")
    public ResponseEntity<EnterpriseDto> getEnterpriseById(@PathVariable Long id) {
        return enterpriseService.findEnterpriseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Neues Unternehmen erstellen
    @PostMapping
    public ResponseEntity<EnterpriseDto> createEnterprise(@RequestBody EnterpriseDto enterpriseDto) {
        EnterpriseDto createdEnterprise = enterpriseService.createEnterprise(enterpriseDto);
        return ResponseEntity.ok(createdEnterprise);
    }

    // PUT: Unternehmen aktualisieren
    @PutMapping("/{id}")
    public ResponseEntity<EnterpriseDto> updateEnterprise(@PathVariable Long id, @RequestBody EnterpriseDto enterpriseDto) {
        try {
            EnterpriseDto updatedEnterprise = enterpriseService.updateEnterprise(id, enterpriseDto);
            return ResponseEntity.ok(updatedEnterprise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Unternehmen löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnterprise(@PathVariable Long id) {
        enterpriseService.deleteEnterprise(id);
        return ResponseEntity.noContent().build();
    }

    // GET: Suche nach Unternehmen basierend auf Kontaktperson
    @GetMapping("/contactPerson/{contactPerson}")
    public ResponseEntity<EnterpriseDto> getEnterpriseByContactPerson(@PathVariable String contactPerson) {
        return enterpriseService.findEnterpriseByContactPerson(contactPerson)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET: Suche nach Unternehmen basierend auf E-Mail-Adresse
    @GetMapping("/emailAddress/{emailAddress}")
    public ResponseEntity<EnterpriseDto> getEnterpriseByEmailAddress(@PathVariable String emailAddress) {
        return enterpriseService.findEnterpriseByEmailAddress(emailAddress)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
