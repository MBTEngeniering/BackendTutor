package com.onlinenachhilfe.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity // Annotation pour une entité JPA
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Nutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private Long id;

    private String anrede;
    private String titel;
    private String nachname;
    private String vorname;
    private String emailadresse;
    private String passwort;
    private String strasse;
    private String hausnummer;
    private String postleitzahl;
    private String telefonnummer;
    private String stadt;
    private String land;
    private String zoomlink;
    @ElementCollection // Spécifie une collection simple
    private List<String> faecher = new ArrayList<>();

    @ElementCollection // Liste des IDs des partenaires
    private List<Long> partner = new ArrayList<>();

    @ElementCollection // Spécifie une collection simple
    private List<String> rolle = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmailadresse() {
        return emailadresse;
    }

    public void setEmailadresse(String emailadresse) {
        this.emailadresse = emailadresse;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public List<String> getFaecher() {
        return faecher;
    }

    public void setFaecher(List<String> faecher) {
        this.faecher = faecher;
    }

    public List<Long> getPartner() {
        return partner;
    }

    public void setPartner(List<Long> partner) {
        this.partner = partner;
    }

    public List<String> getRolle() {
        return rolle;
    }

    public void setRolle(List<String> rolle) {
        this.rolle = rolle;
    }

    public String getZoomlink() {
        return zoomlink;
    }

    public void setZoomlink(String zoomlink) {
        this.zoomlink = zoomlink;
    }
}
