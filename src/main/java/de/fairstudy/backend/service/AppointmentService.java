package de.fairstudy.backend.service;

import de.fairstudy.backend.Utils.UUIDGenerator;
import de.fairstudy.backend.repository.AllUserDao;
import de.fairstudy.backend.repository.AppointmentDao;
import de.fairstudy.backend.dto.AppointmentDTO;
import de.fairstudy.backend.exception.UserNotFoundException;
import de.fairstudy.backend.entity.AllUser;
import de.fairstudy.backend.entity.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    private final AppointmentDao appointmentDao;
    private final AllUserDao tutorDao;

    public AppointmentService(AppointmentDao appointmentDao, AllUserDao tutorDao) {
        this.appointmentDao = appointmentDao;
        this.tutorDao = tutorDao;

    }

    public Appointment createAppointment(AppointmentDTO appointmentDTO) {
        Optional<AllUser> tutor = tutorDao.findById(appointmentDTO.getTutorId());
        Optional<AllUser> student = tutorDao.findById(appointmentDTO.getStudentId());

        if (tutor.isEmpty()) {
            throw new UserNotFoundException("Tutor with ID " + appointmentDTO.getTutorId() + " not found.");
        }

        if (student.isEmpty()) {
            throw new UserNotFoundException("Student with ID " + appointmentDTO.getStudentId() + " not found.");
        }

        UUID uid = new UUIDGenerator().generateUUID();
        appointmentDTO.setId(uid);
        Appointment appointment = convertDtoToEntity(appointmentDTO);
        Appointment savedAppointment = appointmentDao.save(appointment);

        tutor.get().getAppointmentIds().add(savedAppointment.getId());
        student.get().getAppointmentIds().add(savedAppointment.getId());

        tutorDao.save(tutor.get());
        tutorDao.save(student.get());

        return savedAppointment;
    }

    public Appointment updateAppointment(UUID id, AppointmentDTO updatedAppointmentDTO) {
        Optional<Appointment> existingAppointment = appointmentDao.findById(id);
        if (existingAppointment.isEmpty()) {
            throw new UserNotFoundException("Appointment with ID " + id + " not found.");
        }

        Appointment updatedAppointment = convertDtoToEntity(updatedAppointmentDTO);
        updatedAppointment.setId(id); // Sicherstellen, dass die ID gesetzt bleibt
        return appointmentDao.save(updatedAppointment);
    }

    public void deleteAppointment(UUID id) {
        Optional<Appointment> existingAppointment = appointmentDao.findById(id);
        if (existingAppointment.isEmpty()) {
            throw new UserNotFoundException("Appointment with ID " + id + " not found.");
        }

        Appointment appointmentToDelete = existingAppointment.get();

        // Tutor und Student anhand der Appointment-IDs finden
        AllUser tutor = tutorDao.findById(appointmentToDelete.getTutorId())
                .orElseThrow(() -> new UserNotFoundException("Tutor not found for Appointment ID " + id));
        AllUser student = tutorDao.findById(appointmentToDelete.getStudentId())
                .orElseThrow(() -> new UserNotFoundException("Student not found for Appointment ID " + id));

        // Entfernen des Termin-IDs aus der Liste des Tutors und des Schülers
        tutor.getAppointmentIds().remove(id);
        student.getAppointmentIds().remove(id);

        // Speichern der geänderten Tutor- und Schüler-Daten
        tutorDao.save(tutor);
        tutorDao.save(student);

        // Löschen des Termins aus der Appointment-Datenbank
        appointmentDao.deleteById(id);
    }


    public Appointment findById(UUID id) {
        return appointmentDao.findById(id).orElse(null);
    }

    public List<Appointment> findByTutorId(UUID tutorId) {
        return appointmentDao.findByTutorId(tutorId);
    }

    public List<Appointment> findByStudentId(UUID studentId) {
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
        dto.setTutorFeedback(appointment.getTutorFeedback());
        dto.setStudentFeedback(appointment.getStudentFeedback());
        return dto;
    }

    public Appointment convertDtoToEntity(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setStartDate(dto.getStartDate());
        appointment.setEndDate(dto.getEndDate());
        appointment.setTutorFeedback(dto.getTutorFeedback());
        appointment.setStudentFeedback(dto.getStudentFeedback());
        appointment.setTutorId(dto.getTutorId());
        appointment.setStudentId(dto.getStudentId());
        appointment.setStudentIsPresent(dto.getStudentIsPresent());
        appointment.setTutorIsPresent(dto.getTutorIsPresent());
        appointment.setFachId(dto.getFachId());
        appointment.setHourlyRate(dto.getHourlyRate());

        return appointment;
    }
    public Appointment updateStudentIsPresent(UUID id, boolean isPresent) {
        Appointment appointment = findById(id);
        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }
        appointment.setStudentIsPresent(isPresent);
        return appointmentDao.save(appointment);
    }
    public Appointment updateTutorIsPresent(UUID id, boolean isPresent) {
        Appointment appointment = findById(id);
        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }
        appointment.setTutorIsPresent(isPresent);
        return appointmentDao.save(appointment);
    }
    public Appointment updateStudentFeedback(UUID id, String studentFeedback) {
        Appointment appointment = findById(id);
        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }
        appointment.setStudentFeedback(studentFeedback);
        return appointmentDao.save(appointment);
    }
    public Appointment updateTutorFeedback(UUID id, String tutorFeedback) {
        Appointment appointment = findById(id);
        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }
        appointment.setTutorFeedback(tutorFeedback);
        return appointmentDao.save(appointment);
    }


}
