package de.fairstudy.backend.dao;

import de.fairstudy.backend.model.Fach;
import de.fairstudy.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FachDao extends JpaRepository<Fach, Long> {
    Optional<Fach> findFachByFach(Subject fach);

    void deleteFachByFach(Subject fach);
}
