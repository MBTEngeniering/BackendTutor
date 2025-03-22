package com.onlinenachhilfe.backend.dao;

import com.onlinenachhilfe.backend.model.Vertrag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VertragDao extends JpaRepository<Vertrag, Long> {
}
