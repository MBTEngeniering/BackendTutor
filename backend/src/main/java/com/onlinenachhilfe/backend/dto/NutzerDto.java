// 1. Model definiert Entity Aufbau/Struktur
package com.onlinenachhilfe.backend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NutzerDto {
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
    private List<String> faecher = new ArrayList<>(); // Initialisierung der Liste
    private List<Long> partner = new ArrayList<>(); // Initialisierung der Liste
    private List<String> rolle = new ArrayList<>(); // Initialisierung der Liste

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

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
}
