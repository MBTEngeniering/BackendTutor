package de.fairstudy.backend;

import de.fairstudy.backend.batch.AblaufPruefungBatchJob;
import de.fairstudy.backend.batch.AvailabilityCleanupJob;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class BackendApplication implements CommandLineRunner{

    private final AvailabilityCleanupJob availabilityCleanupJob;
    private final AblaufPruefungBatchJob ablaufPruefungBatchJob;

    public BackendApplication(AvailabilityCleanupJob availabilityCleanupJob, AblaufPruefungBatchJob ablaufPruefungBatchJob) {
        this.availabilityCleanupJob = availabilityCleanupJob;
        this.ablaufPruefungBatchJob = ablaufPruefungBatchJob;

    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        System.out.println("Startup time: " + formatter.format(new Date(System.currentTimeMillis())));
        System.out.println("Spring Boot version: " + SpringApplication.class.getPackage().getImplementationVersion());
        System.out.println("▶️ AvailabilityCleanupJob wird direkt beim Start ausgeführt...");
        availabilityCleanupJob.removePastAvailabilities();
        System.out.println("▶️ Abgelaufen Verträge prüfen");
        ablaufPruefungBatchJob.pruefeUndSetzeStunden();


    }
}
