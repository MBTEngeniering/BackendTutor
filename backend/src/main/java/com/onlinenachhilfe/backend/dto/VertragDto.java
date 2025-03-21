package com.onlinenachhilfe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VertragDto {
    private Long vertraggeber;
    private Long vertragnemer;
    private String vertraginhalt;
    private Double stundepreis;
    private String fach;

    public Long getVertraggeber() {
        return vertraggeber;
    }

    public void setVertraggeber(Long vertraggeber) {
        this.vertraggeber = vertraggeber;
    }

    public Long getVertragnemer() {
        return vertragnemer;
    }

    public void setVertragnemer(Long vertragnemer) {
        this.vertragnemer = vertragnemer;
    }

    public String getVertraginhalt() {
        return vertraginhalt;
    }

    public void setVertraginhalt(String vertraginhalt) {
        this.vertraginhalt = vertraginhalt;
    }

    public Double getStundepreis() {
        return stundepreis;
    }


    public void setStundepreis(Double stundepreis) {
        this.stundepreis = stundepreis;

    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }
}
