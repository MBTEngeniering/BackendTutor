package com.onlinenachhilfe.backend.service;

import com.onlinenachhilfe.backend.dao.AppointmentDao;
import com.onlinenachhilfe.backend.dao.StudentDao;
import com.onlinenachhilfe.backend.dao.TutorDao;
import com.onlinenachhilfe.backend.dto.AppointmentDTO;
import com.onlinenachhilfe.backend.exception.UserNotFoundException;
import com.onlinenachhilfe.backend.model.Appointment;
import com.onlinenachhilfe.backend.model.Student;
import com.onlinenachhilfe.backend.model.Tutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentDao appointmentDao;
    private final TutorDao tutorDao;
    private final StudentDao studentDao;

    public AppointmentService(AppointmentDao appointmentDao, TutorDao tutorDao, StudentDao studentDao) {
        this.appointmentDao = appointmentDao;
        this.tutorDao = tutorDao;
        this.studentDao = studentDao;
    }

    public Appointment createAppointment(AppointmentDTO appointmentDTO) {
        Optional<Tutor> tutor = tutorDao.findById(appointmentDTO.getTutorId());
        Optional<Student> student = studentDao.findById(appointmentDTO.getStudentId());

        if (tutor.isEmpty()) {
            throw new UserNotFoundException("Tutor with ID " + appointmentDTO.getTutorId() + " not found.");
        }

        if (student.isEmpty()) {
            throw new UserNotFoundException("Student with ID " + appointmentDTO.getStudentId() + " not found.");
        }


        Appointment appointment = convertDtoToEntity(appointmentDTO);
        Appointment savedAppointment = appointmentDao.save(appointment);


        tutor.get().getAppointmentIds().add(savedAppointment.getId());
        student.get().getAppointmentIds().add(savedAppointment.getId());

        tutorDao.save(tutor.get());
        studentDao.save(student.get());

        return savedAppointment;
    }
    public Appointment updateAppointment(Long id, AppointmentDTO updatedAppointmentDTO) {
        Optional<Appointment> existingAppointment = appointmentDao.findById(id);
        if (existingAppointment.isEmpty()) {
            throw new UserNotFoundException("Appointment with ID " + id + " not found.");
        }

        Appointment updatedAppointment = convertDtoToEntity(updatedAppointmentDTO);
        updatedAppointment.setId(id); // Sicherstellen, dass die ID gesetzt bleibt
        return appointmentDao.save(updatedAppointment);
    }

    public void deleteAppointment(Long id) {
        Optional<Appointment> existingAppointment = appointmentDao.findById(id);
        if (existingAppointment.isEmpty()) {
            throw new UserNotFoundException("Appointment with ID " + id + " not found.");
        }

        Tutor tutor = tutorDao.findById(existingAppointment.get().getTutorId())
                .orElseThrow(() -> new UserNotFoundException("Tutor not found for Appointment ID " + id));
        Student student = studentDao.findById(existingAppointment.get().getStudentId())
                .orElseThrow(() -> new UserNotFoundException("Student not found for Appointment ID " + id));


        tutor.getAppointmentIds().remove(id);
        student.getAppointmentIds().remove(id);

        tutorDao.save(tutor);
        studentDao.save(student);
        appointmentDao.deleteById(id);
    }

    public Appointment findById(Long id) {
        return appointmentDao.findById(id).orElse(null);
    }

    public List<Appointment> findByTutorId(Long tutorId) {
        return appointmentDao.findByTutorId(tutorId);
    }

    public List<Appointment> findByStudentId(Long studentId) {
        return appointmentDao.findByStudentId(studentId);
    }


    public AppointmentDTO convertEntityToDto(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setStartDate(appointment.getStartDate());
        dto.setEndDate(appointment.getEndDate());
        dto.setTutorId(appointment.getTutorId());
        dto.setStudentId(appointment.getStudentId());
        dto.setStudentIsPresent(appointment.getStudentIsPresent());
        dto.setTutorIsPresent(appointment.getTutorIsPresent());
        dto.setFachId(appointment.getFachId());
        dto.setHourlyRate(appointment.getHourlyRate());
        return dto;
    }

    public Appointment convertDtoToEntity(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setStartDate(dto.getStartDate());
        appointment.setEndDate(dto.getEndDate());
        appointment.setTutorId(dto.getTutorId());
        appointment.setStudentId(dto.getStudentId());
        appointment.setStudentIsPresent(dto.getStudentIsPresent());
        appointment.setTutorIsPresent(dto.getTutorIsPresent());
        appointment.setFachId(dto.getFachId());
        appointment.setHourlyRate(dto.getHourlyRate());
        return appointment;
    }
}
