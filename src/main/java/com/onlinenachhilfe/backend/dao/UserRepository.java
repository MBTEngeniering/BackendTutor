package com.onlinenachhilfe.backend.dao;
import com.onlinenachhilfe.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNachname(String nachname);
    Optional<User> findByVorname(String vorname);
    Optional<User> findByTelefonnummer(String telefonnummer);
    Optional<User> findByEmailadresse(String emailadresse);
    Optional<User> findByStrasseAndHausnummerAndPostleitzahl(String strasse, String hausnummer, String postleitzahl);
}
