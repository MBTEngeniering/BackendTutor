package de.fairstudy.backend.batch;

import de.fairstudy.backend.entity.Availability;
import de.fairstudy.backend.repository.AvailabilityDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AvailabilityCleanupJob {

    private final AvailabilityDao availabilityDao;

    @Scheduled(cron = "0 0/30 * * * *") // jede Minute bei Sekunde 0
    public void removePastAvailabilities() {
        log.info("Starte Bereinigung abgelaufener Verfügbarkeiten...");

        List<Availability> allAvailabilities = availabilityDao.findAll();
        log.info("-----------------------------------------------------------Jetzt: {}", LocalDateTime.now());
        for (Availability availability : allAvailabilities) {
            try {
                int day = Integer.parseInt(availability.getTag());
                int month = Integer.parseInt(availability.getMonat());
                int year = Integer.parseInt(availability.getJahr());
                LocalDate date = LocalDate.of(year, month, day);

                LocalTime time = LocalTime.parse(availability.getUhrzeit()); // Format "HH:mm"
                LocalDateTime availabilityDateTime = LocalDateTime.of(date, time);

                if (availabilityDateTime.isBefore(LocalDateTime.now().minusMinutes(5))) {
                    log.info("Lösche Availability mit ID {} weil Termin in Vergangenheit: {}", availability.getId(), availabilityDateTime);
                    availabilityDao.deleteById(availability.getId());
                }

            } catch (Exception e) {
                log.error("Fehler beim Parsen der Availability mit ID {}: {}", availability.getId(), e.getMessage());
            }
        }
    }
}
