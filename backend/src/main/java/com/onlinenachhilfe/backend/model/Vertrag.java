package com.onlinenachhilfe.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vertrag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vertraggeber;
    private Long vertragnemer;
    private String vertraginhalt;
    private Double stundepreis;
    private String fach;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
