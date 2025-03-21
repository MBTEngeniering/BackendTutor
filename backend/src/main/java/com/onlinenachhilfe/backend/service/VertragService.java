package com.onlinenachhilfe.backend.service;

import com.onlinenachhilfe.backend.dao.VertragDao;
import com.onlinenachhilfe.backend.dto.VertragDto;
import com.onlinenachhilfe.backend.model.Vertrag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VertragService {

    @Autowired
    private VertragDao vertragDao;

    public Vertrag saveVertrag(VertragDto vertragDto) {
        Vertrag vertrag = new Vertrag();
        vertrag.setVertraggeber(vertragDto.getVertraggeber());
        vertrag.setVertragnemer(vertragDto.getVertragnemer());
        vertrag.setVertraginhalt(vertragDto.getVertraginhalt());
        vertrag.setStundepreis(vertragDto.getStundepreis());
        vertrag.setFach(vertragDto.getFach());
        return vertragDao.save(vertrag);
    }

    public Optional<Vertrag> findVertragById(Long id) {
        return vertragDao.findById(id);
    }

    public List<Vertrag> findAllVertraege() {
        return vertragDao.findAll();
    }

    public Vertrag updateVertrag(Long id, VertragDto vertragDto) {
        Optional<Vertrag> optionalVertrag = vertragDao.findById(id);
        if (optionalVertrag.isPresent()) {
            Vertrag vertrag = optionalVertrag.get();
            vertrag.setVertraggeber(vertragDto.getVertraggeber());
            vertrag.setVertragnemer(vertragDto.getVertragnemer());
            vertrag.setVertraginhalt(vertragDto.getVertraginhalt());
            vertrag.setStundepreis(vertragDto.getStundepreis());
            return vertragDao.save(vertrag);
        } else {
            throw new RuntimeException("Vertrag mit ID " + id + " nicht gefunden.");
        }
    }

    public void deleteVertragById(Long id) {
        vertragDao.deleteById(id);
    }
}
