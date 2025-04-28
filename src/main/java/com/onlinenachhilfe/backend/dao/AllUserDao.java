package com.onlinenachhilfe.backend.dao;

import com.onlinenachhilfe.backend.model.AllUSer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AllUserDao extends JpaRepository<AllUSer, UUID> {
    List<AllUSer> findByLastName(String lastName);
    List<AllUSer> findByFirstName(String firstName);
    Optional<AllUSer> findByEmailAddress(String emailAddress);
    List<AllUSer> findByStreetAndHouseNumberAndPostalCode(String street, String houseNumber, String postalCode);
    Optional<AllUSer> findByPhoneNumber(String phoneNumber);
    List<AllUSer> findByDateOfBirth(LocalDate dateOfBirth);
    Optional<AllUSer> findByIban(String iban);
    List<AllUSer> findByTaxId(String parentFirstName);

}
