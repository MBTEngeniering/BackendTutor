package com.onlinenachhilfe.backend.dao;

import com.onlinenachhilfe.backend.model.Contract;
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
