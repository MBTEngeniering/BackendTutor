package de.fairstudy.backend.batch;

import de.fairstudy.backend.entity.Appointment;
import de.fairstudy.backend.service.VerdienTabelleService;
import de.fairstudy.backend.repository.AppointmentDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class VerdientAnpassenJob {

    private final AppointmentDao appointmentDao;
    private final VerdienTabelleService verdienTabelleService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Scheduled(fixedRate = 30 * 60 * 1000) // alle 30 Minuten
    public void checkAndCreateVerdienTabelle() {
        log.info("Starte VerdientAnpassenJob...");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twoMinutesBefore = now.minusMinutes(5);
        LocalDateTime twoMinutesAfter = now.plusMinutes(5);

        List<Appointment> allAppointments = appointmentDao.findAll();

        for (Appointment appointment : allAppointments) {
            try {
                LocalDateTime endDateTime = LocalDateTime.parse(appointment.getEndDate(), FORMATTER);
                if (!endDateTime.isBefore(twoMinutesBefore) && !endDateTime.isAfter(twoMinutesAfter)) {
                    System.out.println("-------Erstelle VerdienTabelle für Appointment: {}----"+ appointment.getId());
                    log.info("Erstelle VerdienTabelle für Appointment: {}", appointment.getId());
                    verdienTabelleService.createVerdienTabelle(appointment.getTutorId(), appointment.getId());
                }
            } catch (Exception e) {
                log.error("Fehler beim Verarbeiten von Appointment ID: {}", appointment.getId(), e);
            }
        }

        log.info("VerdientAnpassenJob abgeschlossen.");
    }
}
