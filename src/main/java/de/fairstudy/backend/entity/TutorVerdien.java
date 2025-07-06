package de.fairstudy.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class TutorVerdien {

    @Id
    @GeneratedValue
    private UUID id;

    private int gesamteStunde;
    private double stundentPreise;
    private double gesamtVerdient;
    private UUID tutorId;
}
