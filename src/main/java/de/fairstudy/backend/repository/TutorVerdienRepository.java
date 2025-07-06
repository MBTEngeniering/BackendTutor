package de.fairstudy.backend.repository;

import de.fairstudy.backend.entity.TutorVerdien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TutorVerdienRepository extends JpaRepository<TutorVerdien, UUID> {
    Optional<TutorVerdien> findByTutorId(UUID tutorId);
}
