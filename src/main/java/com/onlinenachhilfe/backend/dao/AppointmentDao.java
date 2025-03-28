package com.onlinenachhilfe.backend.dao;

import com.onlinenachhilfe.backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Long> {
    List<Appointment> findByTutorId(Long tutorId);
    List<Appointment> findByStudentId(Long studentId);
}
