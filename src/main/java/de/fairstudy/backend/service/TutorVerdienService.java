package de.fairstudy.backend.service;

import de.fairstudy.backend.entity.TutorVerdien;
import de.fairstudy.backend.repository.TutorVerdienRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TutorVerdienService {

    private final TutorVerdienRepository repository;

    public TutorVerdienService(TutorVerdienRepository repository) {
        this.repository = repository;
    }

    public TutorVerdien createVerdien(UUID tutorId) {
        // Prüfen, ob bereits vorhanden
        Optional<TutorVerdien> existing = repository.findByTutorId(tutorId);
        if (existing.isPresent()) {
            throw new IllegalStateException("Verdien für diesen Tutor existiert bereits!");
        }

        TutorVerdien verdien = new TutorVerdien();
        verdien.setTutorId(tutorId);
        verdien.setGesamteStunde(0);
        verdien.setStundentPreise(15.0);
        verdien.setGesamtVerdient(15.0);

        return repository.save(verdien);
    }

    public Optional<TutorVerdien> updateVerdien(UUID id) {
        Optional<TutorVerdien> optionalVerdien = repository.findById(id);
        if (optionalVerdien.isPresent()) {
            TutorVerdien verdien = optionalVerdien.get();
            int neueStunden = verdien.getGesamteStunde() + 1;
            verdien.setGesamteStunde(neueStunden);

            double stundentPreise = verdien.getStundentPreise();
            if (stundentPreise < 20 && neueStunden > 49) {
                stundentPreise += (int) (neueStunden / 50);
                verdien.setStundentPreise(stundentPreise);
            }

            verdien.setGesamtVerdient(neueStunden * stundentPreise);

            repository.save(verdien);
        }
        return optionalVerdien;
    }

    public Optional<TutorVerdien> getVerdienByTutorId(UUID tutorId) {
        return repository.findByTutorId(tutorId);
    }
}
