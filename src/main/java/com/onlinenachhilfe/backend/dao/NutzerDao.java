package com.onlinenachhilfe.backend.dao;
import com.onlinenachhilfe.backend.model.Nutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutzerDao extends JpaRepository<Nutzer, Long> {


}