package de.fairstudy.backend.repository;

import de.fairstudy.backend.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ContractDao extends JpaRepository<Contract, UUID> {
    List<Contract> findByOwnerId(UUID ownerId);
    List<Contract> findByCreationDate(Date creationDate);
}
