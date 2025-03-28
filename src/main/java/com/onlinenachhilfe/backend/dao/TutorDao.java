package com.onlinenachhilfe.backend.dao;

import com.onlinenachhilfe.backend.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TutorDao extends JpaRepository<Tutor, Long> {
    List<Tutor> findByLastName(String lastName);
    List<Tutor> findByFirstName(String firstName);
    Optional<Tutor> findByEmailAddress(String emailAddress);
    List<Tutor> findByStreetAndHouseNumberAndPostalCode(String street, String houseNumber, String postalCode);
    Optional<Tutor> findByPhoneNumber(String phoneNumber);
    List<Tutor> findByDateOfBirth(LocalDate dateOfBirth);
    Optional<Tutor> findByIban(String iban);
    List<Tutor> findByTaxId(String parentFirstName);

}
