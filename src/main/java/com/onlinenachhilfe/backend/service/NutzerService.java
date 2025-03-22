package com.onlinenachhilfe.backend.service;

import com.onlinenachhilfe.backend.dao.NutzerDao;
import com.onlinenachhilfe.backend.model.Nutzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutzerService {

    private final NutzerDao nutzerDao;

    public NutzerService(NutzerDao nutzerDao) {
        this.nutzerDao = nutzerDao;
    }

    public Nutzer saveNutzer(Nutzer nutzer) {
        return nutzerDao.save(nutzer);
    }


    public Optional<Nutzer> findNutzerById(Long id) {
        return nutzerDao.findById(id);
    }


    public List<Nutzer> findNutzerByNachname(String nachname) {
        return nutzerDao.findAll().stream()
                .filter(nutzer -> nachname.equalsIgnoreCase(nutzer.getNachname()))
                .toList();
    }


    public List<Nutzer> findNutzerByVorname(String vorname) {
        return nutzerDao.findAll().stream()
                .filter(nutzer -> vorname.equalsIgnoreCase(nutzer.getVorname()))
                .toList();
    }

    public Optional<Nutzer> findNutzerByEmail(String emailadresse) {
        return nutzerDao.findAll().stream()
                .filter(nutzer -> emailadresse.equalsIgnoreCase(nutzer.getEmailadresse()))
                .findFirst();
    }


    public Optional<Nutzer> findNutzerByTelefonnummer(String telefonnummer) {
        return nutzerDao.findAll().stream()
                .filter(nutzer -> telefonnummer.equalsIgnoreCase(nutzer.getTelefonnummer()))
                .findFirst();
    }


    public List<Nutzer> findNutzerByAdresse(String hausnummer, String strasse, String land, String postleitzahl, String stadt) {
        return nutzerDao.findAll().stream()
                .filter(nutzer -> hausnummer.equalsIgnoreCase(nutzer.getHausnummer()) &&
                        strasse.equalsIgnoreCase(nutzer.getStrasse()) &&
                        land.equalsIgnoreCase(nutzer.getLand()) &&
                        postleitzahl.equalsIgnoreCase(nutzer.getPostleitzahl()) &&
                        stadt.equalsIgnoreCase(nutzer.getStadt()))
                .toList();
    }


    public Nutzer updateNutzer(Long id, Nutzer updatedNutzer) {
        Optional<Nutzer> existingNutzer = nutzerDao.findById(id);
        if (existingNutzer.isPresent()) {
            Nutzer nutzer = existingNutzer.get();
            nutzer.setAnrede(updatedNutzer.getAnrede());
            nutzer.setTitel(updatedNutzer.getTitel());
            nutzer.setNachname(updatedNutzer.getNachname());
            nutzer.setVorname(updatedNutzer.getVorname());
            nutzer.setEmailadresse(updatedNutzer.getEmailadresse());
            nutzer.setPasswort(updatedNutzer.getPasswort());
            nutzer.setStrasse(updatedNutzer.getStrasse());
            nutzer.setHausnummer(updatedNutzer.getHausnummer());
            nutzer.setPostleitzahl(updatedNutzer.getPostleitzahl());
            nutzer.setStadt(updatedNutzer.getStadt());
            nutzer.setLand(updatedNutzer.getLand());
            nutzer.setFaecher(updatedNutzer.getFaecher());
            nutzer.setPartner(updatedNutzer.getPartner());
            nutzer.setRolle(updatedNutzer.getRolle());
            nutzer.setZoomlink(updatedNutzer.getZoomlink());
            return nutzerDao.save(nutzer);
        } else {
            throw new RuntimeException("Nutzer avec ID " + id + " introuvable.");
        }
    }


    public void deleteNutzerById(Long id) {
        nutzerDao.deleteById(id);
    }

    public List<Nutzer> findAllNutzer() {
        return nutzerDao.findAll();
    }
}
