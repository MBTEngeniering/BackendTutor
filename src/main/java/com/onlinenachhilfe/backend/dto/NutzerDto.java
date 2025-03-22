// 1. Model definiert Entity Aufbau/Struktur
package com.onlinenachhilfe.backend.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"passwort"})
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
}
