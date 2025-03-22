package com.onlinenachhilfe.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true, value = {"passwort"})
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
}
