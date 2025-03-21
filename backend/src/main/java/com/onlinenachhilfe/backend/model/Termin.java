package com.onlinenachhilfe.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startDate;
    private String endDate;

    private String schueler;
    private String lehrer;

    private Boolean schuelerIsPresent;
    private Boolean lehrerIsPresent;
    private String fach;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSchueler() {
        return schueler;
    }

    public void setSchueler(String schueler) {
        this.schueler = schueler;
    }

    public String getLehrer() {
        return lehrer;
    }

    public void setLehrer(String lehrer) {
        this.lehrer = lehrer;
    }

    public Boolean getSchuelerIsPresent() {
        return schuelerIsPresent;
    }

    public void setSchuelerIsPresent(Boolean schuelerIsPresent) {
        this.schuelerIsPresent = schuelerIsPresent;
    }

    public Boolean getLehrerIsPresent() {
        return lehrerIsPresent;
    }

    public void setLehrerIsPresent(Boolean lehrerIsPresent) {
        this.lehrerIsPresent = lehrerIsPresent;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }
}
