package de.fairstudy.backend.service;

import de.fairstudy.backend.entity.MeineStunde;
import de.fairstudy.backend.repository.MeineStundeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MeineStundeService {

    private final MeineStundeRepository repository;

    public MeineStunde create(MeineStunde meineStunde) {
        return repository.save(meineStunde);
    }

    public Optional<MeineStunde> findById(UUID id) {
        return repository.findById(id);
    }

    public List<MeineStunde> findByUserId(UUID userId) {
        return repository.findByUserId(userId);
    }

    public MeineStunde update(MeineStunde meineStunde) {
        return repository.save(meineStunde);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<MeineStunde> findAll() {
        return repository.findAll();
    }
}
