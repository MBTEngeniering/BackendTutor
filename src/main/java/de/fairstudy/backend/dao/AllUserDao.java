package de.fairstudy.backend.dao;

import de.fairstudy.backend.model.AllUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AllUserDao extends JpaRepository<AllUser, UUID> {
    List<AllUser> findByLastName(String lastName);

    List<AllUser> findByFirstName(String firstName);

    Optional<AllUser> findByEmailAddress(String emailAddress);

    List<AllUser> findByStreetAndHouseNumberAndPostalCode(String street, String houseNumber, String postalCode);

    Optional<AllUser> findByPhoneNumber(String phoneNumber);

    List<AllUser> findByDateOfBirth(LocalDate dateOfBirth);

    Optional<AllUser> findByIban(String iban);

    List<AllUser> findByTaxId(String parentFirstName);
}
