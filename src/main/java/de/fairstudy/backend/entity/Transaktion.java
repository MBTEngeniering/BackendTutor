package de.fairstudy.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaktion {

    @Id
    @GeneratedValue
    private UUID id;

    private LocalDateTime transaktionDatum;

    private double transaktionBetrag;

    private UUID personId;

    private boolean transaktionBestaetigt;

}
