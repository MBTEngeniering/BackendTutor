package de.fairstudy.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class VerdienTabelle {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID appointementId;
    private double hourlyId;
    private String status;
}
