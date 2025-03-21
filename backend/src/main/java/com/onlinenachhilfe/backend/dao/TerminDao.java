package com.onlinenachhilfe.backend.dao;

import com.onlinenachhilfe.backend.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminDao extends JpaRepository<Termin, Long> {
}
