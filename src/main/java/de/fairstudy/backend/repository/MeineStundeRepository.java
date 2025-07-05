package de.fairstudy.backend.repository;

import de.fairstudy.backend.entity.MeineStunde;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MeineStundeRepository extends JpaRepository<MeineStunde, UUID> {

    List<MeineStunde> findByUserId(UUID userId);
}
