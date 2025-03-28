package com.onlinenachhilfe.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onlinenachhilfe.backend.model.Contract;

@Repository
public interface ContractDao extends JpaRepository<Contract, Long> {

}
