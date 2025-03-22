package com.onlinenachhilfe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminDto {
    private String startDate;
    private String endDate;
    private String schueler;
    private String lehrer;
    private Boolean schuelerIsPresent;
    private Boolean lehrerIsPresent;
    private String fach;

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
