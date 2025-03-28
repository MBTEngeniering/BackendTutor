package com.onlinenachhilfe.backend.dao;

import com.onlinenachhilfe.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
    List<Student> findByLastName(String lastName);
    List<Student> findByFirstName(String firstName);
    Optional<Student> findByEmailAddress(String emailAddress);
    List<Student> findByStreetAndHouseNumberAndPostalCode(String street, String houseNumber, String postalCode);
    Optional<Student> findByPhoneNumber(String phoneNumber);
    List<Student> findByDateOfBirth(LocalDate dateOfBirth);
    Optional<Student> findByIban(String iban);
    List<Student> findByParentFirstName(String parentFirstName);

}
