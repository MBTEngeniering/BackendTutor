package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.ContractDto;
import com.onlinenachhilfe.backend.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getContract(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContractDto> createContract(@RequestBody ContractDto contractDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.create(contractDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractDto> updateContract(@PathVariable Long id, @RequestBody ContractDto contractDto) {
        return ResponseEntity.ok(contractService.update(id, contractDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        contractService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
