package com.onlinenachhilfe.backend.controller;

import com.onlinenachhilfe.backend.dto.ContractDto;
import com.onlinenachhilfe.backend.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping
    public ContractDto createContract(@RequestBody ContractDto contractDto) {
        return contractService.saveContract(contractDto);
    }

    @GetMapping("/{id}")
    public ContractDto getContractById(@PathVariable UUID id) {
        return contractService.getContractById(id)
                .orElseThrow(() -> new NoSuchElementException("Contract with ID " + id + " not found"));
    }

    @GetMapping("/owner/{ownerId}")
    public List<ContractDto> getContractsByOwnerId(@PathVariable UUID ownerId) {
        return contractService.getContractsByOwnerId(ownerId);
    }

    @GetMapping("/date")
    public List<ContractDto> getContractsByDate(
            @RequestParam("creationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date creationDate) {
        return contractService.getContractsByCreationDate(creationDate);
    }

    @GetMapping
    public List<ContractDto> getAllContracts() {
        return contractService.getAllContracts();
    }

    @PutMapping
    public ContractDto updateContract(@RequestBody ContractDto contractDto) {
        return contractService.updateContract(contractDto);
    }

    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable UUID id) {
        contractService.deleteContract(id);
    }
}
