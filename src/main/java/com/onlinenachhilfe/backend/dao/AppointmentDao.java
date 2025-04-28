package com.onlinenachhilfe.backend.dao;

import com.onlinenachhilfe.backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByTutorId(UUID tutorId);
    List<Appointment> findByStudentId(UUID studentId);
}
