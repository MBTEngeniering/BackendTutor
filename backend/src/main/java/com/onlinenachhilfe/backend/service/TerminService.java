package com.onlinenachhilfe.backend.service;

import com.onlinenachhilfe.backend.dao.TerminDao;
import com.onlinenachhilfe.backend.dto.TerminDto;
import com.onlinenachhilfe.backend.model.Termin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TerminService {

    @Autowired
    private TerminDao terminDao;

    public Termin saveTermin(TerminDto terminDto) {
        Termin termin = new Termin();
        termin.setStartDate(terminDto.getStartDate());
        termin.setEndDate(terminDto.getEndDate());
        termin.setSchueler(terminDto.getSchueler());
        termin.setLehrer(terminDto.getLehrer());
        termin.setSchuelerIsPresent(terminDto.getSchuelerIsPresent());
        termin.setLehrerIsPresent(terminDto.getLehrerIsPresent());
        termin.setFach(terminDto.getFach());
        return terminDao.save(termin);
    }

    public Optional<Termin> findTerminById(Long id) {
        return terminDao.findById(id);
    }

    public List<Termin> findAllTermine() {
        return terminDao.findAll();
    }

    public Termin updateTermin(Long id, TerminDto terminDto) {
        Optional<Termin> existingTermin = terminDao.findById(id);
        if (existingTermin.isPresent()) {
            Termin termin = existingTermin.get();
            termin.setStartDate(terminDto.getStartDate());
            termin.setEndDate(terminDto.getEndDate());
            termin.setSchueler(terminDto.getSchueler());
            termin.setLehrer(terminDto.getLehrer());
            termin.setSchuelerIsPresent(terminDto.getSchuelerIsPresent());
            termin.setLehrerIsPresent(terminDto.getLehrerIsPresent());
            termin.setFach(terminDto.getFach());
            return terminDao.save(termin);
        } else {
            throw new RuntimeException("Termin avec ID " + id + " introuvable.");
        }
    }

    public void deleteTerminById(Long id) {
        terminDao.deleteById(id);
    }
}

