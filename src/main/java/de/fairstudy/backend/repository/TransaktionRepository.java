package de.fairstudy.backend.repository;

import de.fairstudy.backend.entity.Transaktion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransaktionRepository extends JpaRepository<Transaktion, UUID> {
    List<Transaktion> findByPersonId(UUID personId);
}
