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

    @GetMapping("/{id}")
    public ResponseEntity<EnterpriseDto> getEnterpriseById(@PathVariable Long id) {
        return enterpriseService.findEnterpriseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EnterpriseDto> createEnterprise(@RequestBody EnterpriseDto enterpriseDto) {
        EnterpriseDto createdEnterprise = enterpriseService.createEnterprise(enterpriseDto);
        return ResponseEntity.ok(createdEnterprise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnterpriseDto> updateEnterprise(@PathVariable Long id, @RequestBody EnterpriseDto enterpriseDto) {
        try {
            EnterpriseDto updatedEnterprise = enterpriseService.updateEnterprise(id, enterpriseDto);
            return ResponseEntity.ok(updatedEnterprise);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnterprise(@PathVariable Long id) {
        enterpriseService.deleteEnterprise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/contactPerson/{contactPerson}")
    public ResponseEntity<EnterpriseDto> getEnterpriseByContactPerson(@PathVariable String contactPerson) {
        return enterpriseService.findEnterpriseByContactPerson(contactPerson)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/emailAddress/{emailAddress}")
    public ResponseEntity<EnterpriseDto> getEnterpriseByEmailAddress(@PathVariable String emailAddress) {
        return enterpriseService.findEnterpriseByEmailAddress(emailAddress)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
