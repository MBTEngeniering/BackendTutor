package com.onlinenachhilfe.backend.service;

import com.onlinenachhilfe.backend.dao.EnterpriseDao;
import com.onlinenachhilfe.backend.dto.EnterpriseDto;
import com.onlinenachhilfe.backend.model.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseService {

    private final EnterpriseDao enterpriseDao;

    @Autowired
    public EnterpriseService(EnterpriseDao enterpriseDao) {
        this.enterpriseDao = enterpriseDao;
    }

    // Methode zum Konvertieren von Dto zu Entity
    public Enterprise convertDtoToEntity(EnterpriseDto dto) {
        Enterprise enterprise = new Enterprise();
        enterprise.setId(dto.getId());
        enterprise.setCompanyName(dto.getCompanyName());
        enterprise.setRegistrationNumber(dto.getRegistrationNumber());
        enterprise.setTaxNumber(dto.getTaxNumber());
        enterprise.setWebsite(dto.getWebsite());
        enterprise.setContactPerson(dto.getContactPerson());
        enterprise.setEmailAddress(dto.getEmailAddress());
        enterprise.setPhoneNumber(dto.getPhoneNumber());
        enterprise.setAddress(dto.getAddress());
        enterprise.setDescription(dto.getDescription());
        return enterprise;
    }

    // Methode zum Konvertieren von Entity zu Dto
    public EnterpriseDto convertEntityToDto(Enterprise enterprise) {
        return new EnterpriseDto(
                enterprise.getId(),
                enterprise.getCompanyName(),
                enterprise.getRegistrationNumber(),
                enterprise.getTaxNumber(),
                enterprise.getWebsite(),
                enterprise.getContactPerson(),
                enterprise.getEmailAddress(),
                enterprise.getPhoneNumber(),
                enterprise.getAddress(),
                enterprise.getDescription()
        );
    }

    // Alle Unternehmen finden
    public List<EnterpriseDto> findAllEnterprises() {
        List<Enterprise> enterprises = enterpriseDao.findAll();
        return enterprises.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    // Unternehmen nach ID finden
    public Optional<EnterpriseDto> findEnterpriseById(Long id) {
        return enterpriseDao.findById(id)
                .map(this::convertEntityToDto);
    }

    // Unternehmen erstellen
    public EnterpriseDto createEnterprise(EnterpriseDto dto) {
        Enterprise enterprise = convertDtoToEntity(dto);
        Enterprise savedEnterprise = enterpriseDao.save(enterprise);
        return convertEntityToDto(savedEnterprise);
    }

    // Unternehmen aktualisieren
    public EnterpriseDto updateEnterprise(Long id, EnterpriseDto dto) {
        Optional<Enterprise> optionalEnterprise = enterpriseDao.findById(id);
        if (optionalEnterprise.isPresent()) {
            Enterprise existingEnterprise = optionalEnterprise.get();
            existingEnterprise.setCompanyName(dto.getCompanyName());
            existingEnterprise.setRegistrationNumber(dto.getRegistrationNumber());
            existingEnterprise.setTaxNumber(dto.getTaxNumber());
            existingEnterprise.setWebsite(dto.getWebsite());
            existingEnterprise.setContactPerson(dto.getContactPerson());
            existingEnterprise.setEmailAddress(dto.getEmailAddress());
            existingEnterprise.setPhoneNumber(dto.getPhoneNumber());
            existingEnterprise.setAddress(dto.getAddress());
            existingEnterprise.setDescription(dto.getDescription());
            Enterprise updatedEnterprise = enterpriseDao.save(existingEnterprise);
            return convertEntityToDto(updatedEnterprise);
        }
        throw new IllegalArgumentException("Enterprise not found with ID: " + id);
    }

    // Unternehmen löschen
    public void deleteEnterprise(Long id) {
        enterpriseDao.deleteById(id);
    }
    // Suche nach Unternehmen basierend auf Kontaktperson
    public Optional<EnterpriseDto> findEnterpriseByContactPerson(String contactPerson) {
        return enterpriseDao.findByContactPerson(contactPerson)
                .map(this::convertEntityToDto);
    }

    // Suche nach Unternehmen basierend auf E-Mail-Adresse
    public Optional<EnterpriseDto> findEnterpriseByEmailAddress(String emailAddress) {
        return enterpriseDao.findByEmailAddress(emailAddress)
                .map(this::convertEntityToDto);
    }
}
