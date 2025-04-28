package de.fairstudy.backend.service;

import de.fairstudy.backend.dao.FachDao;
import de.fairstudy.backend.model.Fach;
import de.fairstudy.backend.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FachService {

    private final FachDao fachDao;

    @Autowired
    public FachService(FachDao fachDao) {
        this.fachDao = fachDao;
    }

    // Fach nach ID suchen
    public Optional<Fach> findFachById(Long id) {
        return fachDao.findById(id);
    }

    // Fach nach Fach-Enum suchen
    public Optional<Fach> findFachByFach(Subject fach) {
        return fachDao.findFachByFach(fach);
    }

    // Neues Fach erstellen
    public Fach createFach(Subject fach) {
        Fach newFach = new Fach();
        newFach.setFach(fach);
        return fachDao.save(newFach);
    }

    // Fach nach ID löschen
    public void deleteFachById(Long id) {
        fachDao.deleteById(id);
    }

    // Fach nach Fach-Enum löschen
    public void deleteFachByFach(Subject fach) {
        fachDao.deleteFachByFach(fach);
    }

    // Fach aktualisieren
    public Fach updateFach(Long id, Subject newFach) {
        Optional<Fach> optionalFach = fachDao.findById(id);
        if (optionalFach.isPresent()) {
            Fach fach = optionalFach.get();
            fach.setFach(newFach);
            return fachDao.save(fach);
        } else {
            throw new IllegalArgumentException("Fach with ID " + id + " not found");
        }
    }
    public List<Fach> getAllFaecher() {
        return fachDao.findAll(); // Ruft alle Fächer aus der Datenbank ab
    }
}
