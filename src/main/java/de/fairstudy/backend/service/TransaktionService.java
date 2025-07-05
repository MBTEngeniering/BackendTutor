package de.fairstudy.backend.service;

import de.fairstudy.backend.entity.Transaktion;
import de.fairstudy.backend.repository.TransaktionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class TransaktionService {

    private final TransaktionRepository repository;

    public TransaktionService(TransaktionRepository repository) {
        this.repository = repository;
    }

    public Transaktion speichereTransaktion(Transaktion transaktion) {
        return repository.save(transaktion);
    }

    public List<Transaktion> findeAlleTransaktionen() {
        return repository.findAll();
    }

    public Transaktion findeTransaktionNachId(UUID id) {
        return repository.findById(id).orElse(null);
    }
    public List<Transaktion> findeTransaktionenNachPersonId(UUID personId) {
        return repository.findByPersonId(personId);
    }

}
