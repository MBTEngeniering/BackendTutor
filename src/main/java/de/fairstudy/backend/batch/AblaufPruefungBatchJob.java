package de.fairstudy.backend.batch;

import de.fairstudy.backend.entity.MeineStunde;
import de.fairstudy.backend.service.MeineStundeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AblaufPruefungBatchJob {

    private final MeineStundeService service;

    @Scheduled(cron = "0 0 0 * * ?") // täglich 0 Uhr
    public void pruefeUndSetzeStunden() {
        LocalDate heute = LocalDate.now();
        List<MeineStunde> alleStunden = service.findAll();

        for (MeineStunde stunde : alleStunden) {
            if (stunde.getDatumAblauf() != null && !heute.isBefore(stunde.getDatumAblauf())) {
                if (stunde.getAnzahlStunde() != null && stunde.getAnzahlStunde() > 0) {
                    stunde.setAnzahlStunde(0L);
                    service.update(stunde);
                }
            }
        }
    }
}
