package de.fairstudy.backend.repository;

import de.fairstudy.backend.entity.VerdienTabelle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VerdienTabelleRepository extends JpaRepository<VerdienTabelle, UUID> {
    List<VerdienTabelle> findByAppointementId(UUID appointementId);
}
