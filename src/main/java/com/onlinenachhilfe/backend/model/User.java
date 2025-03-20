package com.onlinenachhilfe.backend.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String anrede;

    private String titel;

    @NotBlank
    private String nachname;

    @NotBlank
    private String vorname;

    @NotBlank
    private String strasse;

    @NotBlank
    private String hausnummer;

    @NotBlank
    private String postleitzahl;

    @NotBlank
    private String stadt;

    @NotBlank
    private String land;

    @Email
    @Column(unique = true)
    private String emailadresse;

    @NotBlank
    private String passwort;

    @NotBlank
    @Column(unique = true)
    private String telefonnummer;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Rolle> rolle;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getAnrede() {
        return anrede;
    }

    public void setAnrede(@NotBlank String anrede) {
        this.anrede = anrede;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public @NotBlank String getNachname() {
        return nachname;
    }

    public void setNachname(@NotBlank String nachname) {
        this.nachname = nachname;
    }

    public @NotBlank String getVorname() {
        return vorname;
    }

    public void setVorname(@NotBlank String vorname) {
        this.vorname = vorname;
    }

    public @NotBlank String getStrasse() {
        return strasse;
    }

    public void setStrasse(@NotBlank String strasse) {
        this.strasse = strasse;
    }

    public @NotBlank String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(@NotBlank String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public @NotBlank String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(@NotBlank String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public @NotBlank String getStadt() {
        return stadt;
    }

    public void setStadt(@NotBlank String stadt) {
        this.stadt = stadt;
    }

    public @NotBlank String getLand() {
        return land;
    }

    public void setLand(@NotBlank String land) {
        this.land = land;
    }

    public @Email String getEmailadresse() {
        return emailadresse;
    }

    public void setEmailadresse(@Email String emailadresse) {
        this.emailadresse = emailadresse;
    }

    public @NotBlank String getPasswort() {
        return passwort;
    }

    public void setPasswort(@NotBlank String passwort) {
        this.passwort = passwort;
    }

    public @NotBlank String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(@NotBlank String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public Set<Rolle> getRolle() {
        return rolle;
    }

    public void setRolle(Set<Rolle> rolle) {
        this.rolle = rolle;
    }
}
