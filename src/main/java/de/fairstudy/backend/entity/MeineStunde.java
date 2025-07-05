package de.fairstudy.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class MeineStunde {

    @Id
    @GeneratedValue
    private UUID id;

    private Long anzahlStunde;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<UUID> transaktionIds;

    private LocalDate datumBuchen;

    private LocalDate datumAblauf;

    private Boolean gueltigkeit;

    private UUID userId;
}
