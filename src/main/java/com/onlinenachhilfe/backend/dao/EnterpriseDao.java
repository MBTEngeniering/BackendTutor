package com.onlinenachhilfe.backend.dao;

import com.onlinenachhilfe.backend.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseDao extends JpaRepository<Enterprise, Long> {

    Optional<Enterprise> findByCompanyName(String companyName);
    Optional<Enterprise> findByRegistrationNumber(String registrationNumber);
    Optional<Enterprise> findByContactPerson(String contactPerson);
    Optional<Enterprise> findByEmailAddress(String emailAddress);
}
