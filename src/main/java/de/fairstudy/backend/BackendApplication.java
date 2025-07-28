package de.fairstudy.backend;

import de.fairstudy.backend.batch.AblaufPruefungBatchJob;
import de.fairstudy.backend.batch.AvailabilityCleanupJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class BackendApplication implements CommandLineRunner {

    private final AvailabilityCleanupJob availabilityCleanupJob;
    private final AblaufPruefungBatchJob ablaufPruefungBatchJob;
    @Value("${spring.profiles.active}")  // Injects property from active profile
    private String profile;

    private final static String DEV_PROFILE = "dev";
    private final static String PROD_PROFILE = "prod";

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
        if (profile != null && !profile.isEmpty()) {
            System.out.println("Active profile: " + profile);

            if (profile.equals(DEV_PROFILE)) {
                System.out.println("⚠️ Development profile detected! ⚠️");
                System.out.println("▶️ AvailabilityCleanupJob wird direkt beim Start ausgeführt...");
                availabilityCleanupJob.removePastAvailabilities();
                System.out.println("▶️ Abgelaufen Verträge prüfen");
                ablaufPruefungBatchJob.pruefeUndSetzeStunden();
            } else if (profile.equals(PROD_PROFILE)) {
                System.out.println("Production profile is active.");
            }
        }
    }
}
