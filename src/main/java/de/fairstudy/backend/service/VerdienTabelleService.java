package de.fairstudy.backend.service;

import de.fairstudy.backend.entity.Appointment;
import de.fairstudy.backend.entity.TutorVerdien;
import de.fairstudy.backend.entity.VerdienTabelle;
import de.fairstudy.backend.repository.AppointmentDao;
import de.fairstudy.backend.repository.TutorVerdienRepository;
import de.fairstudy.backend.repository.VerdienTabelleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VerdienTabelleService {

    private final TutorVerdienRepository tutorVerdienRepository;
    private final AppointmentDao appointmentDao;
    private final VerdienTabelleRepository verdienTabelleRepository;

    public VerdienTabelleService(TutorVerdienRepository tutorVerdienRepository, AppointmentDao appointmentDao, VerdienTabelleRepository verdienTabelleRepository) {
        this.tutorVerdienRepository = tutorVerdienRepository;
        this.appointmentDao = appointmentDao;
        this.verdienTabelleRepository = verdienTabelleRepository;
    }


    public VerdienTabelle createVerdienTabelle(UUID tutorId, UUID appointmentId) {
        TutorVerdien verdien = tutorVerdienRepository.findByTutorId(tutorId)
                .orElseGet(() -> {
                    TutorVerdien neuVerdien = new TutorVerdien();
                    neuVerdien.setTutorId(tutorId);
                    neuVerdien.setGesamteStunde(0);
                    neuVerdien.setStundentPreise(15.0);
                    neuVerdien.setGesamtVerdient(15.0);
                    return tutorVerdienRepository.save(neuVerdien);
                });

        Optional<Appointment> optionalAppointment = appointmentDao.findById(appointmentId);
        if (optionalAppointment.isEmpty()) {
            throw new IllegalArgumentException("Appointment nicht gefunden");
        }

        Appointment appointment = optionalAppointment.get();

        VerdienTabelle vt = new VerdienTabelle();
        vt.setAppointementId(appointmentId);
        vt.setTutorId(appointment.getTutorId());
        vt.setStudentId(appointment.getStudentId());

        if (appointment.getStudentIsPresent() && appointment.getTutorIsPresent()) {
            vt.setHourlyId(verdien.getStundentPreise());
            vt.setStatus("erfolg");

            int neueStunden = verdien.getGesamteStunde() + 1;
            verdien.setGesamteStunde(neueStunden);

            double stundentPreise = verdien.getStundentPreise();
            if (stundentPreise < 20 && neueStunden > 49) {
                stundentPreise += (int) (neueStunden / 50);
                verdien.setStundentPreise(stundentPreise);
            }

            verdien.setGesamtVerdient(neueStunden * stundentPreise);

            tutorVerdienRepository.save(verdien);

        } else if (!appointment.getStudentIsPresent() && appointment.getTutorIsPresent()) {
            vt.setHourlyId(5);
            vt.setStatus("nur_tutor");
        }
        else if (!appointment.getTutorIsPresent()&&appointment.getStudentIsPresent()) {
            vt.setHourlyId(-10);
            vt.setStatus("Student");
        }else {
            vt.setHourlyId(0);
            vt.setStatus("keiner");
        }

        return verdienTabelleRepository.save(vt);
    }

    public List<VerdienTabelle> getVerdienByAppointementId(UUID appointementId) {
        return verdienTabelleRepository.findByAppointementId(appointementId);
    }
    public List<VerdienTabelle> getVerdienByTutorId(UUID TutorId) {
        return verdienTabelleRepository.findByTutorId(TutorId);
    }
    public List<VerdienTabelle> getVerdienByStudentId(UUID StudentId) {
        return verdienTabelleRepository.findByStudentId(StudentId);
    }
}
