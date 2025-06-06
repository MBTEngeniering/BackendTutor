package de.fairstudy.backend.repository;

import de.fairstudy.backend.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AvailabilityDao extends JpaRepository<Availability, Long> {
    List<Availability> findByUserId(UUID userId);
    boolean existsByUserIdAndTagAndMonatAndJahrAndUhrzeit(UUID userId, String tag, String monat, String jahr, String uhrzeit);

}
